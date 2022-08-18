package com.ssafy.backend.controller;

import com.ssafy.backend.dto.ErrorDto;
import com.ssafy.backend.entity.Interest;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.UserRepository;
import com.ssafy.backend.service.UserInterestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserInterestController {

    private final UserInterestService userInterestService;
    private final UserRepository userRepository;

    public UserInterestController(UserInterestService userInterestService, UserRepository userRepository) {
        this.userInterestService = userInterestService;
        this.userRepository = userRepository;
    }

    //사용자가 선택한 관심사 정보 조회
    @GetMapping("/like/{username}")
    public ResponseEntity<?> getUserInterestInfo(@PathVariable("username") String username){
        User user = userRepository.findUserByUserEmail(username);
        if(user == null){
            return ResponseEntity.ok(new ErrorDto(401,"존재하지 않는 회원입니다"));
        }else{
            return new ResponseEntity<List<Interest>>(userInterestService.getUserInterest(username), HttpStatus.OK);
        }

    }

    //사용자 관심사 정보 생성/업데이트
    @PostMapping("/like/{username}")
    public ResponseEntity<?> updateUserInterestInfo(@PathVariable("username") String username, @RequestBody List<Interest> interestList){
        User user = userRepository.findUserByUserEmail(username);
        if(user == null){
            return ResponseEntity.ok(new ErrorDto(401,"존재하지 않는 회원입니다"));
        }else{
            return new ResponseEntity<>(userInterestService.updateUserInterest(username,interestList), HttpStatus.OK);
        }
    }
}
