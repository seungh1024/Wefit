package com.ssafy.backend.controller;

import com.ssafy.backend.service.OpenviduService;
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

    OpenviduService openviduService;

    //생성자
    public webRtcController(@Value("${openvidu.secret}") String secret, @Value("${openvidu.url}") String openviduUrl) {
        openviduService = new OpenviduService(secret, openviduUrl);
    }

    @PostMapping("/api/v1/joinSession")
    public ResponseEntity<JSONObject> joinSession(@RequestBody String sessionNameParam, HttpSession httpSession) throws ParseException {
        //세션에서 세션 정보 받아서 파싱 - 세션 정보 (방의 기준이 됨)
        JSONObject sessionJSON = (JSONObject) new JSONParser().parse(sessionNameParam);
        String sessionName = (String) sessionJSON.get("sessionNameParam");

        JSONObject responseJson = openviduService.joinSession(sessionName, httpSession);

        return new ResponseEntity<>(responseJson, HttpStatus.OK);
    }

    @PostMapping("/api/v1/createSession")
    public ResponseEntity<JSONObject> createSession(HttpSession httpSession) throws ParseException {
        //세션에서 세션 정보 받아서 파싱 - 세션 정보 (방의 기준이 됨)

        JSONObject responseJson = openviduService.createSession(httpSession);

        return new ResponseEntity<>(responseJson, HttpStatus.OK);
    }

    // 방 나가기
//    @RequestMapping(value = "/api/v1/remove-user", method = RequestMethod.POST)
//    public ResponseEntity<JSONObject> removeUser(@RequestBody String sessionNameToken, HttpSession httpSession)
//            throws Exception {
//
//        // Retrieve the params from BODY
//        // 받은 데이터 파싱 작업
//        JSONObject sessionNameTokenJSON = (JSONObject) new JSONParser().parse(sessionNameToken);
//        String sessionName = (String) sessionNameTokenJSON.get("sessionName");
//        String token = (String) sessionNameTokenJSON.get("token");
//
//        // If the session exists
//        if (this.mapSessions.get(sessionName) != null && this.mapSessionNamesTokens.get(sessionName) != null) {
//            // If the token exists
//            if (this.mapSessionNamesTokens.get(sessionName).remove(token) != null) { // 토큰 제거
//                // User left the session
//                if (this.mapSessionNamesTokens.get(sessionName).isEmpty()) { //세션에 모든 사람이 나간경우
//                    // Last user left: session must be removed
//                    this.mapSessions.remove(sessionName); // 저장된 세션 제거
//                }
//                return new ResponseEntity<>(HttpStatus.OK);
//            } else {
//                // The TOKEN wasn't valid
//                System.out.println("Problems in the app server: the TOKEN wasn't valid");
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//
//        } else {
//            // The SESSION does not exist
//            System.out.println("Problems in the app server: the SESSION does not exist");
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    private ResponseEntity<JSONObject> getErrorResponse(Exception e) {
        JSONObject json = new JSONObject();
        json.put("cause", e.getCause());
        json.put("error", e.getMessage());
        json.put("exception", e.getClass());
        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


