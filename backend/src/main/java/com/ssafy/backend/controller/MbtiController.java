package com.ssafy.backend.controller;


import com.ssafy.backend.dto.ErrorDto;
import com.ssafy.backend.entity.Mbti;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.UserRepository;
import com.ssafy.backend.service.HateMbtiService;
import com.ssafy.backend.service.MbtiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MbtiController {

    private final MbtiService mbtiService;
    private final HateMbtiService hateMbtiService;
    private final UserRepository userRepository;

    public MbtiController(MbtiService mbtiService, HateMbtiService hateMbtiService, UserRepository userRepository) {
        this.mbtiService = mbtiService;
        this.hateMbtiService = hateMbtiService;
        this.userRepository = userRepository;
    }

    // 전체 mbti 리스트 조회
    @GetMapping("/mbti")
    public ResponseEntity<List<Mbti>> getAllMbti(){
        return new ResponseEntity<>(mbtiService.getAllMbti(), HttpStatus.OK);
    }

    // 사용자 비선호 mbti 리스트 조회
    @GetMapping("/mbti/{username}")
    public ResponseEntity<?> getUserHateMbtiInfo(@PathVariable("username") String username){
        User user = userRepository.findUserByUserEmail(username);
        if(user == null){
            return ResponseEntity.ok(new ErrorDto(401,"존재하지 않는 회원입니다"));
        }else{
            return new ResponseEntity<List<Mbti>>(hateMbtiService.getUserHateMbtis(username), HttpStatus.OK);
        }

    }

    // 사용자 비선호 mbti 생성/업데이트
    @PostMapping("/mbti/{username}")
    public ResponseEntity<?> updateUserHateMbtiInfo(@PathVariable("username") String username, @RequestBody List<Mbti> hateMbtiList){


        User user = userRepository.findUserByUserEmail(username);
        if(user == null){
            return ResponseEntity.ok(new ErrorDto(401,"존재하지 않는 회원입니다"));
        }else{
            return new ResponseEntity<>(hateMbtiService.updateUserHateMbti(username,hateMbtiList), HttpStatus.OK);
        }


    }
}
