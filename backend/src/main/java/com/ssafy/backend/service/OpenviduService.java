package com.ssafy.backend.service;

import io.openvidu.java.client.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OpenviduService {
    private OpenVidu openVidu;
    // 사용중인 세션 관리 객체 (세션 이름, 세션 토큰 쌍)
    private Map<String, Session> mapSessions = new HashMap<>();
    // 사용자와 세션 관리 객체
    private Map<String, Map<String, OpenViduRole>> mapSessionNamesTokens = new HashMap<>();
    // openvidu url
    private String OPENVIDU_URL;
    // openvidu secret
    private String SECRET;
    public OpenviduService(){};
    //생성자
    public OpenviduService(String secret, String openviduUrl) {
        this.SECRET = secret;
        this.OPENVIDU_URL = openviduUrl;
        this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);
    }

    //Openvidu connection Properties 생성
    public ConnectionProperties createConnectionProperties(HttpSession httpSession) {
        String serverData = "{\"serverData\": \"" + httpSession.getAttribute("loggedUser") + "\"}";
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .type(ConnectionType.WEBRTC)
                .data(serverData)
                .role(OpenViduRole.PUBLISHER) //역할 입력 - 일단 publisher로 통합시킴
                .kurentoOptions( //faceOverlayfilter를 사용할때 필요한 것으로 판단됨
                        new KurentoOptions.Builder()
                                .allowedFilters(new String[]{"GStreamerFilter", "FaceOverlayFilter"})
                                .build())
                .build();
        return connectionProperties;
    }

    //Openvidu 새로운 세션 생성
    public JSONObject createSession(HttpSession httpSession) {
        ConnectionProperties connectionProperties = createConnectionProperties(httpSession);
        try {
            // Create a new OpenVidu Session
            Session session = this.openVidu.createSession();
            // Generate a new Connection with the recently created connectionProperties
            // 커넥션 생성
            String token = session.createConnection(connectionProperties).getToken();
            JSONObject responseJson = new JSONObject();

            String sessionName = createRandName(15); //15자리의 랜덤 문자열
            while (mapSessions.get(sessionName) != null){ // 중복 방지
                sessionName = createRandName(15);
            }
            System.out.println("Random Session Name: " + sessionName);

            // Store the session and the token in our collections
            // 세션 정보 입력
            this.mapSessions.put(sessionName, session);
            this.mapSessionNamesTokens.put(sessionName, new ConcurrentHashMap<>());
            this.mapSessionNamesTokens.get(sessionName).put(token, OpenViduRole.PUBLISHER);

            // Prepare the response with the token
            responseJson.put("token", token);
            responseJson.put("sessionName", sessionName);

            // Return the response to the client
            // 토큰정보와 상태 정보 리턴
            return responseJson;

        } catch (Exception e) {
            // If error generate an error message and return it to client
            return getErrorResponse(e);
        }
    }

    // 해당 세션에 참여
    public JSONObject joinSession(String sessionName, HttpSession httpSession) {
        ConnectionProperties connectionProperties = createConnectionProperties(httpSession);
        try {
            // Generate a new Connection with the recently created connectionProperties
            // 커넥션 생성
            String token = this.mapSessions.get(sessionName).createConnection(connectionProperties).getToken();

            // Update our collection storing the new token
            // 새로운 토큰 정보 저장
            this.mapSessionNamesTokens.get(sessionName).put(token, OpenViduRole.PUBLISHER); // 우선 역할은 publisher로 통일

            JSONObject responseJson = new JSONObject();
            // Prepare the response with the token
            responseJson.put("token", token);

            // Return the response to the client
            // 토큰 정보와 상태 정보 리턴
            return responseJson;
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
            return getErrorResponse(e2);
        }
    }

    // 방 나가기
    public HttpStatus exitSession(String sessionName, String token){
        // If the session exists
        if (this.mapSessions.get(sessionName) != null && this.mapSessionNamesTokens.get(sessionName) != null) {
            // If the token exists
            if (this.mapSessionNamesTokens.get(sessionName).remove(token) != null) { // 토큰 제거
                // User left the session
                if (this.mapSessionNamesTokens.get(sessionName).isEmpty()) { //세션에 모든 사람이 나간경우
                    // Last user left: session must be removed
                    this.mapSessions.remove(sessionName); // 저장된 세션 제거
                }
                return HttpStatus.OK;
            } else {
                // The TOKEN wasn't valid
                System.out.println("Problems in the app server: the TOKEN wasn't valid");
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

        } else {
            // The SESSION does not exist
            System.out.println("Problems in the app server: the SESSION does not exist");
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    private JSONObject getErrorResponse(Exception e) {
        JSONObject json = new JSONObject();
        json.put("cause", e.getCause());
        json.put("error", e.getMessage());
        json.put("exception", e.getClass());
        return json;
    }

    // 랜덤 문자열 생성
    private String createRandName(int i){
// bind the length
        byte[] bytearray;
        bytearray = new byte[256];
        String mystring;
        StringBuffer thebuffer;
        String theAlphaNumericS;

        new Random().nextBytes(bytearray);

        mystring = new String(bytearray, Charset.forName("UTF-8"));

        thebuffer = new StringBuffer();

        //remove all spacial char
        theAlphaNumericS
                = mystring
                .replaceAll("[^A-Z0-9]", "");

        //random selection
        for (int m = 0; m < theAlphaNumericS.length(); m++) {

            if (Character.isLetter(theAlphaNumericS.charAt(m))
                    && (i > 0)
                    || Character.isDigit(theAlphaNumericS.charAt(m))
                    && (i > 0)) {

                thebuffer.append(theAlphaNumericS.charAt(m));
                i--;
            }
        }

        // the resulting string
        return thebuffer.toString();
    }
}
