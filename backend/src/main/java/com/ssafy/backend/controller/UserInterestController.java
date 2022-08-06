package com.ssafy.backend.controller;

import com.ssafy.backend.entity.Interest;
import com.ssafy.backend.entity.UserInterest;
import com.ssafy.backend.service.UserInterestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserInterestController {

    private final UserInterestService userInterestService;


    public UserInterestController(UserInterestService userInterestService) {
        this.userInterestService = userInterestService;
    }

    //사용자가 선택한 관심사 정보 조회
    @GetMapping("/like/{username}")
    public ResponseEntity<List<Interest>> getUserInterestInfo(@PathVariable("username") String username){
        return new ResponseEntity<>(userInterestService.getUserInterest(username), HttpStatus.OK);
    }

    //사용자 관심사 정보 생성/업데이트
    @PostMapping("/like/{username}")
    public ResponseEntity<?> updateUserInterestInfo(@PathVariable("username") String username, @RequestBody List<Interest> interestList){

        return new ResponseEntity<>(userInterestService.updateUserInterest(username,interestList), HttpStatus.OK);

    }


}
