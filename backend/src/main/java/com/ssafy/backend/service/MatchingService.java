package com.ssafy.backend.service;

import org.json.simple.JSONObject;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class MatchingService {

    private final SimpMessageSendingOperations messagingTemplate;

    public MatchingService(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // 매칭 리스트에 사람 추가
    public void appendMatchingList(String userEmail, String mbti){

    }

    // 매칭 알고리즘 돌리기
    public void matchingAlgo(){

    }

    // 사용자 비교로 점수 판단
    public void calMatchingPoint(){

    }

    //매칭 성공시 동작
    public void matchingSuccess(){

    }

    // 매칭된 대상에게 메시지 날리기 (/sub/유저이메일 로 연결된 소켓에 메시지 보냄)
    public void completeMatchingMessage(String userEmail){
        JSONObject result = new JSONObject();
        result.put("userEmail", userEmail);
        result.put("sessionToken", "asdf");
        messagingTemplate.convertAndSend("/sub/" + userEmail,result  );
    }


}
