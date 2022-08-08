package com.ssafy.backend.service;

import io.openvidu.java.client.*;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class OpenviduService {
    private OpenVidu openVidu;
    // 사용중인 세션 관리 객체 (세션 이름, 세션 토큰 쌍)
    private Map<String, Session> mapSessions = new HashMap<>();
    // 세션이름 세션 토큰 유저 이메일
    private Map<String, Map<String, String>> mapSessionNamesTokensRand = new HashMap<>();
    private Map<String, Map<String, String>> mapSessionNamesTokens = new HashMap<>();
    // 유저 이름, <세션이름, 세션토큰>
    private Map<String, Map<String, String>> mapUserSession = new HashMap<>();
    // openvidu url
    private String OPENVIDU_URL;
    // openvidu secret
    private String SECRET;

    private Queue<String> matchingQueue;

    private SimpMessagingTemplate messagingTemplate;
    public OpenviduService(){
    };

    //생성자
    public OpenviduService(String secret, String openviduUrl, SimpMessagingTemplate messagingTemplate) {
        this.SECRET = secret;
        this.OPENVIDU_URL = openviduUrl;
        this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);
        this.matchingQueue = new LinkedList<>();
        this.messagingTemplate = messagingTemplate;
    }

    //Openvidu connection Properties 생성
    public ConnectionProperties createConnectionProperties(String userEmail) {
        String serverData = "{\"serverData\": \"" + userEmail + "\"}";
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
    public JSONObject createSession(String userEmail) {
        ConnectionProperties connectionProperties = createConnectionProperties(userEmail);
        try {
            // Create a new OpenVidu Session
            Session session = this.openVidu.createSession();
            // Generate a new Connection with the recently created connectionProperties

            System.out.println(3);

            // 커넥션 생성
            String token = session.createConnection(connectionProperties).getToken();
            JSONObject responseJson = new JSONObject();

            String sessionName = createRandName(15); //15자리의 랜덤 문자열
            while (mapSessions.get(sessionName) != null){ // 중복 방지
                sessionName = createRandName(15);
            }

            // Store the session and the token in our collections
            // 세션 정보 입력
            this.mapSessions.put(sessionName, session);
            this.mapSessionNamesTokens.put(sessionName, new ConcurrentHashMap<>());
            this.mapSessionNamesTokens.get(sessionName).put(token, userEmail);
//            this.mapUserSession.put(userEmail, new HashMap<>());
//            this.mapUserSession.get(userEmail).put(sessionName, token);
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
    public JSONObject joinSession(String sessionName, String userEmail) {
        ConnectionProperties connectionProperties = createConnectionProperties(userEmail);
        try {
            // Generate a new Connection with the recently created connectionProperties
            // 커넥션 생성
            String token = this.mapSessions.get(sessionName).createConnection(connectionProperties).getToken();

            // Update our collection storing the new token
            // 새로운 토큰 정보 저장
            this.mapSessionNamesTokens.get(sessionName).put(token, userEmail); // 우선 역할은 publisher로 통일
//            this.mapUserSession.put(userEmail, new HashMap<>());
//            this.mapUserSession.get(userEmail).put(sessionName, token);

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

    // 매칭 리스트에 사람 추가
    public void appendMatchingList(String userEmail){
        matchingQueue.offer(userEmail);
        matchingAlgo();
    }

    // 매칭 알고리즘 돌리기
    public int matchingAlgo(){
        if (matchingQueue.size()>=2){
            String roomCreator = matchingQueue.poll();
            String roomAttendant = matchingQueue.poll();

            System.out.println(roomCreator + " " + roomAttendant);

            JSONObject objCreator = createSession(roomCreator);

            JSONObject objAttendant = joinSession((String) objCreator.get("sessionName"), roomAttendant);

            completeMatchingMessage(roomCreator, objCreator);
            completeMatchingMessage(roomAttendant, objAttendant);
            return 1;
        }
        return 0;
    }

    // 매칭된 대상에게 메시지 날리기 (/sub/유저이메일 로 연결된 소켓에 메시지 보냄)
    public void completeMatchingMessage(String userEmail, JSONObject obj){
        System.out.println(messagingTemplate);
        messagingTemplate.convertAndSend("/sub/matching/" + userEmail, obj);
    }

    public JSONObject getRoomInfo(){

        List<Map<String, Integer>> roomInfo = new ArrayList<>();
        JSONObject responseJson = new JSONObject();
        int idx = 0;
        for (String key : mapSessionNamesTokens.keySet()){
            JSONObject tmp = new JSONObject();
            tmp.put("sessionName", key);
            tmp.put("userNum", mapSessionNamesTokens.get(key).size());
            responseJson.put(idx++, tmp);
        }

        return responseJson;
    }
}
