package com.ssafy.backend.util;

import com.ssafy.backend.service.HateMbtiService;
import com.ssafy.backend.service.OpenviduService;
import com.ssafy.backend.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MatchingUtil implements Runnable{

    private String myEmail;
    private String myMBTI;
    private List<String> myHateMBTI;
//    private List<String> myInterest;

    private OpenviduService openviduService;
    public MatchingUtil(String myEmail, String myMBTI, List<String> myHateMBTI, String secret, String openviduUrl, HateMbtiService hateMbtiService, UserDetailService userDetailService, SimpMessagingTemplate messagingTemplate){
        this.myEmail = myEmail;
        this.myMBTI = myMBTI;
        this.myHateMBTI = myHateMBTI;
//        this.myInterest = myInterest;
        this.openviduService = new OpenviduService(secret, openviduUrl, messagingTemplate, userDetailService, hateMbtiService);
    }

    @Override
    public void run() {
        int point = 0;
        System.out.println(myEmail + " " + myMBTI + " " + myHateMBTI.toString());
        while (true){
            if (OpenviduService.matchingQueue.get(myEmail) == null) return;
            point = 0;
            System.out.println(myEmail + " " + OpenviduService.matchingQueue.size());
            //map 탐색
            for (ConcurrentHashMap.Entry<String, Map<String, List<String>>> cur : OpenviduService.matchingQueue.entrySet()){
                if (OpenviduService.matchingQueue.get(myEmail) == null) return;
                // map에서 필요한 정보들 가져오기
                String userEmail = cur.getKey();

                //자기 자신은 넘어가기
                if (userEmail.equals(myEmail)) continue;

                Map<String, List<String>> userInfo = cur.getValue();

                String userMBTI = userInfo.get("userMBTI").get(0);
                List<String> userHateMBTI = userInfo.get("userHateMBTI");
//              List<String> userInterest = userInfo.get("userinterest");

                // MBTI 비교
                if (myHateMBTI.contains(userMBTI) || userHateMBTI.contains(myMBTI)){
                    continue;
                }
                System.out.println("---- MBTI 꺼리김 없음 ----");
                if (OpenviduService.matchingQueue.get(myEmail) != null && OpenviduService.matchingQueue.get(myEmail) != null){
                    System.out.println("user 1 : " + myEmail);
                    System.out.println("user 2 : " + userEmail);
                    OpenviduService.matchingQueue.remove(myEmail);
                    OpenviduService.matchingQueue.remove(userEmail);
                }else{
                    continue;
                }
                openviduService.matchingEnd(myEmail, userEmail);
                return;
               // 관심사 비교
//               else {
//                   for (String s : myInterest){
//                       if (userInterest.contains(s)){
//                           point++;
//                       }
//                   }
//                   if (point >= 2) {
//                       // TODO: 2022-08-09 매칭 완료
//                       System.out.println("-----------------");
//                   }else{
//                       continue;
//                   }
//               }
            }
            // 과부화 방지용..? 1초 휴식
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
