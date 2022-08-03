package com.ssafy.backend.controller;

import com.ssafy.backend.service.MatchingService;
import com.ssafy.backend.service.OpenviduService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class webRtcController {

    private final OpenviduService openviduService;
    private final MatchingService matchingService;

    //생성자
    public webRtcController(@Value("${openvidu.secret}") String secret, @Value("${openvidu.url}") String openviduUrl, MatchingService matchingService) {
        this.matchingService = matchingService;
        openviduService = new OpenviduService(secret, openviduUrl);
    }

    @PostMapping("/api/v1/createSession")
    public ResponseEntity<JSONObject> createSession(HttpSession httpSession) throws ParseException {
        //세션에서 세션 정보 받아서 파싱 - 세션 정보 (방의 기준이 됨)

        JSONObject responseJson = openviduService.createSession(httpSession);

        return new ResponseEntity<>(responseJson, HttpStatus.OK);
    }

    @PostMapping("/api/v1/joinSession")
    public ResponseEntity<JSONObject> joinSession(@RequestBody String sessionNameParam, HttpSession httpSession) throws ParseException {
        //세션에서 세션 정보 받아서 파싱 - 세션 정보 (방의 기준이 됨)
        JSONObject sessionJSON = (JSONObject) new JSONParser().parse(sessionNameParam);
        String sessionName = (String) sessionJSON.get("sessionNameParam");

        JSONObject responseJson = openviduService.joinSession(sessionName, httpSession);

        return new ResponseEntity<>(responseJson, HttpStatus.OK);
    }

    // 방 나가기
    @RequestMapping(value = "/api/v1/exitRoom", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> removeUser(@RequestBody String sessionNameToken, HttpSession httpSession)
            throws Exception {

        // Retrieve the params from BODY
        // 받은 데이터 파싱 작업
        JSONObject sessionNameTokenJSON = (JSONObject) new JSONParser().parse(sessionNameToken);
        String sessionName = (String) sessionNameTokenJSON.get("sessionName");
        String token = (String) sessionNameTokenJSON.get("token");

        return new ResponseEntity<>(openviduService.exitSession(sessionName, token));
    }

    // 방 매칭 요청
    @PostMapping("/api/v1/matching/{userEmail}")
    public String matching(@PathVariable("userEmail") String userEmail) {

        // 해당 이메일을 가지는 사용자의 MBTI 가져오기
//        String mbti =

        // 매칭 테이블에 해당 이메일, mbti 저장 (매칭 대기 map =>  유저이메일 : mbti)
//        matchingService.appendMatchingList(userEmail, mbti);

        return "매칭을 시작합니다.";
    }

    private ResponseEntity<JSONObject> getErrorResponse(Exception e) {
        JSONObject json = new JSONObject();
        json.put("cause", e.getCause());
        json.put("error", e.getMessage());
        json.put("exception", e.getClass());
        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


