package com.ssafy.backend.controller;

import com.ssafy.backend.service.HateMbtiService;
import com.ssafy.backend.service.OpenviduService;
import com.ssafy.backend.service.UserDetailService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class webRtcController {

    private final OpenviduService openviduService;
    private final HateMbtiService hateMbtiService;
    private final UserDetailService userDetailService;
    @Autowired
    private final SimpMessagingTemplate  messagingTemplate;

    //생성자
    public webRtcController(@Value("${openvidu.secret}") String secret, @Value("${openvidu.url}") String openviduUrl, HateMbtiService hateMbtiService, UserDetailService userDetailService, SimpMessagingTemplate  messagingTemplate) {
        this.hateMbtiService = hateMbtiService;
        this.userDetailService = userDetailService;
        this.messagingTemplate = messagingTemplate;
        this.openviduService = new OpenviduService(secret, openviduUrl, messagingTemplate, userDetailService, hateMbtiService);
    }

    @PostMapping("/api/v1/getToken")
    public ResponseEntity<JSONObject> getToken(@RequestBody Map<String, String> data) throws ParseException {
        String sessionName = data.get("sessionName");
        String userEmail = data.get("userEmail");

        JSONObject responseJson = openviduService.joinSession(sessionName, userEmail);

        return new ResponseEntity<>(responseJson, HttpStatus.OK);
    }

    @GetMapping("/api/v1/getRoomInfo")
    public ResponseEntity<JSONObject> getRoomInfo(){

        JSONObject responseJson = openviduService.getRoomInfo();

        return new ResponseEntity<>(responseJson, HttpStatus.OK);
    }

    @PostMapping("/api/v1/createSession")
    public ResponseEntity<JSONObject> createSession(@RequestBody Map<String, String> user) throws ParseException {
        //세션에서 세션 정보 받아서 파싱 - 세션 정보 (방의 기준이 됨)

        String userEmail = user.get("userEmail");
        JSONObject responseJson = openviduService.createSession(userEmail);

        return new ResponseEntity<>(responseJson, HttpStatus.OK);
    }

    @PostMapping("/api/v1/joinSession")
    public ResponseEntity<JSONObject> joinSession(@RequestBody Map<String, String> data) throws ParseException {
        String sessionNameParam = data.get("sessionNameParam");
        String userEmail = data.get("userEmail");

        //세션에서 세션 정보 받아서 파싱 - 세션 정보 (방의 기준이 됨)
        JSONObject sessionJSON = (JSONObject) new JSONParser().parse(sessionNameParam);
        String sessionName = (String) sessionJSON.get("sessionNameParam");

        JSONObject responseJson = openviduService.joinSession(sessionName, userEmail);

        return new ResponseEntity<>(responseJson, HttpStatus.OK);
    }

    // 방 나가기
    @RequestMapping(value = "/api/v1/exitRoom", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> removeUser(@RequestBody String sessionNameToken)
            throws Exception {
        // Retrieve the params from BODY
        // 받은 데이터 파싱 작업
        JSONObject sessionNameTokenJSON = (JSONObject) new JSONParser().parse(sessionNameToken);
        String sessionName = (String) sessionNameTokenJSON.get("sessionName");
        String token = (String) sessionNameTokenJSON.get("token");

        return new ResponseEntity<>(openviduService.exitSession(sessionName, token));
    }

    // 방 매칭 요청
    @PostMapping("/api/v1/matching")
    public String matching(@RequestBody Map<String, String> data) {
        System.out.println("controller 1 ---");
        String userEmail = data.get("userEmail");
        // 해당 이메일을 가지는 사용자의 MBTI 가져오기
        // String mbti =
        System.out.println("controller 2 ---");
        // 매칭 테이블에 해당 이메일, mbti 저장 (매칭 대기 map =>  유저이메일 : mbti)
        openviduService.appendMatchingList(userEmail);
        System.out.println("controller 3 ---");
        return "매칭을 시작합니다.";
    }

    @GetMapping("/api/v1/roomInfo")
    public String roomInfo(){

        return "ddd";
    }

    private ResponseEntity<JSONObject> getErrorResponse(Exception e) {
        JSONObject json = new JSONObject();
        json.put("cause", e.getCause());
        json.put("error", e.getMessage());
        json.put("exception", e.getClass());
        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}


