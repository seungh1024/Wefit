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
@RequestMapping("/api/v1/webrtc")
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

    // TODO: 2022-08-10 아래 주석 컨트롤러 처리하기
    @GetMapping("/getRoomInfo")
    public ResponseEntity<?> getRoomInfo(){
        return openviduService.getRoomInfo();
    }

    @PostMapping("/createSession")
    public ResponseEntity<?> createSession(@RequestBody Map<String, String> user) throws ParseException {
        //세션에서 세션 정보 받아서 파싱 - 세션 정보 (방의 기준이 됨)
        String userEmail = user.get("userEmail");
        return  openviduService.getcreateSession(userEmail);
    }

    @PostMapping("/joinSession")
    public ResponseEntity<?> joinSession(@RequestBody Map<String, String> data) throws ParseException {
        String sessionNameParam = data.get("sessionNameParam");
        String userEmail = data.get("userEmail");

        //세션에서 세션 정보 받아서 파싱 - 세션 정보 (방의 기준이 됨)
        JSONObject sessionJSON = (JSONObject) new JSONParser().parse(sessionNameParam);
        String sessionName = (String) sessionJSON.get("sessionNameParam");

        return openviduService.getJoinSession(sessionName, userEmail);
    }

    // 방 나가기
    @RequestMapping(value = "/exitRoom", method = RequestMethod.POST)
    public ResponseEntity<?> removeUser(@RequestBody String sessionNameToken)
            throws Exception {
        // Retrieve the params from BODY
        // 받은 데이터 파싱 작업
        JSONObject sessionNameTokenJSON = (JSONObject) new JSONParser().parse(sessionNameToken);
        String sessionName = (String) sessionNameTokenJSON.get("sessionName");
        String token = (String) sessionNameTokenJSON.get("token");

        return openviduService.exitSession(sessionName, token);
    }

    // 방 매칭 요청
    @PostMapping("/matching")
    public ResponseEntity<?> matching(@RequestBody Map<String, String> data) {
        String userEmail = data.get("userEmail");
        // 해당 이메일을 가지는 사용자의 MBTI 가져오기
        // String mbti =
        // 매칭 테이블에 해당 이메일, mbti 저장 (매칭 대기 map =>  유저이메일 : mbti)
        return openviduService.appendMatchingList(userEmail);
    }

    @PostMapping("/exitMatching")
    public ResponseEntity<?> exitMatching(@RequestBody Map<String, String> data) {
        String userEmail = data.get("userEmail");
        // 해당 이메일을 가지는 사용자의 MBTI 가져오기
        // String mbti =
        // 매칭 테이블에 해당 이메일, mbti 저장 (매칭 대기 map =>  유저이메일 : mbti)
        return openviduService.exitMatching(userEmail);
    }

    private ResponseEntity<JSONObject> getErrorResponse(Exception e) {
        JSONObject json = new JSONObject();
        json.put("cause", e.getCause());
        json.put("error", e.getMessage());
        json.put("exception", e.getClass());
        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}


