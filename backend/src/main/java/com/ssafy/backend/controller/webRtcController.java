package com.ssafy.backend.controller;

import io.openvidu.java.client.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class webRtcController {
    //오픈비두 객체
    private OpenVidu openVidu;
    // 사용중인 세션 관리 객체 (세션 이름, 세션 토큰 쌍)
    private Map<String, Session> mapSessions = new HashMap<>();
    // 사용자와 세션 관리 객체
    private Map<String, Map<String, OpenViduRole>> mapSessionNamesTokens = new HashMap<>();

    // openvidu url
    private String OPENVIDU_URL;
    // openvidu secret
    private String SECRET;

    //생성자
    public webRtcController(@Value("${openvidu.secret}") String secret, @Value("${openvidu.url}") String openviduUrl) {
        this.SECRET = secret;
        this.OPENVIDU_URL = openviduUrl;
        this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);
    }

    @PostMapping("/api/v1/get-seesionToken")
    public ResponseEntity<JSONObject> getSessionToken(@RequestBody String sessionNameParam, HttpSession httpSession) throws ParseException {
        //세션에서 세션 정보 받아서 파싱 - 세션 정보 (방의 기준이 됨)
        JSONObject sessionJSON = (JSONObject) new JSONParser().parse(sessionNameParam);
        String sessionName = (String) sessionJSON.get("sessionNameParam");

        String serverData = "{\"serverData\": \"" + httpSession.getAttribute("loggedUser") + "\"}";

        //connectionProperties 객체 생성
        // Build connectionProperties object with the serverData and the role
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .type(ConnectionType.WEBRTC)
                .data(serverData)
                .role(OpenViduRole.PUBLISHER) //역할 입력 - 일단 publisher로 통합시킴
                .kurentoOptions( //faceOverlayfilter를 사용할때 필요한 것으로 판단됨
                        new KurentoOptions.Builder()
                                .allowedFilters(new String[]{"GStreamerFilter", "FaceOverlayFilter"})
                                .build())
                .build();
        JSONObject responseJson = new JSONObject();

        if (mapSessions.get(sessionName) != null) { //세션 이름이 있는 경우 (이미 만들어진 기록이 있는 경우)
            // Session already exists
            System.out.println("Existing session " + sessionName);
            try {
                // Generate a new Connection with the recently created connectionProperties
                // 커넥션 생성
                String token = this.mapSessions.get(sessionName).createConnection(connectionProperties).getToken();

                // Update our collection storing the new token
                // 새로운 토큰 정보 저장
                this.mapSessionNamesTokens.get(sessionName).put(token, OpenViduRole.PUBLISHER); // 우선 역할은 publisher로 통일

                // Prepare the response with the token
                responseJson.put(0, token);

                // Return the response to the client
                // 토큰 정보와 상태 정보 리턴
                return new ResponseEntity<>(responseJson, HttpStatus.OK);
            } catch (OpenViduJavaClientException e1) {
                // If internal error generate an error message and return it to client
                return getErrorResponse(e1);
            } catch (OpenViduHttpException e2) {
                if (404 == e2.getStatus()) {
                    // Invalid sessionId (user left unexpectedly). Session object is not valid
                    // anymore. Clean collections and continue as new session
                    this.mapSessions.remove(sessionName); //해당 정보 제거
                    this.mapSessionNamesTokens.remove(sessionName); // 해당 정보 제거
                }
            }
        }

        // 세션 이름이 없는 경우 - 세션 생성
        // New session
        System.out.println("New session " + sessionName);
        try {
            // Create a new OpenVidu Session
            Session session = this.openVidu.createSession();
            // Generate a new Connection with the recently created connectionProperties
            // 커넥션 생성
            String token = session.createConnection(connectionProperties).getToken();

            // Store the session and the token in our collections
            // 세션 정보 입력
            this.mapSessions.put(sessionName, session);
            this.mapSessionNamesTokens.put(sessionName, new ConcurrentHashMap<>());
            this.mapSessionNamesTokens.get(sessionName).put(token, OpenViduRole.PUBLISHER);

            // Prepare the response with the token
            responseJson.put(0, token);

            // Return the response to the client
            // 토큰정보와 상태 정보 리턴
            return new ResponseEntity<>(responseJson, HttpStatus.OK);

        } catch (Exception e) {
            // If error generate an error message and return it to client
            return getErrorResponse(e);
        }
    }

    // 방 나가기
    @RequestMapping(value = "/api/v1/remove-user", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> removeUser(@RequestBody String sessionNameToken, HttpSession httpSession)
            throws Exception {

        // Retrieve the params from BODY
        // 받은 데이터 파싱 작업
        JSONObject sessionNameTokenJSON = (JSONObject) new JSONParser().parse(sessionNameToken);
        String sessionName = (String) sessionNameTokenJSON.get("sessionName");
        String token = (String) sessionNameTokenJSON.get("token");

        // If the session exists
        if (this.mapSessions.get(sessionName) != null && this.mapSessionNamesTokens.get(sessionName) != null) {
            // If the token exists
            if (this.mapSessionNamesTokens.get(sessionName).remove(token) != null) { // 토큰 제거
                // User left the session
                if (this.mapSessionNamesTokens.get(sessionName).isEmpty()) { //세션에 모든 사람이 나간경우
                    // Last user left: session must be removed
                    this.mapSessions.remove(sessionName); // 저장된 세션 제거
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // The TOKEN wasn't valid
                System.out.println("Problems in the app server: the TOKEN wasn't valid");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            // The SESSION does not exist
            System.out.println("Problems in the app server: the SESSION does not exist");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<JSONObject> getErrorResponse(Exception e) {
        JSONObject json = new JSONObject();
        json.put("cause", e.getCause());
        json.put("error", e.getMessage());
        json.put("exception", e.getClass());
        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


