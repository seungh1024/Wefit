package com.ssafy.backend.controller;


import com.ssafy.backend.entity.Interest;
import com.ssafy.backend.entity.Mbti;
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
    public MbtiController(MbtiService mbtiService, HateMbtiService hateMbtiService) {
        this.mbtiService = mbtiService;
        this.hateMbtiService = hateMbtiService;
    }

    // 전체 mbti 리스트 조회
    @GetMapping("/mbti")
    public ResponseEntity<List<Mbti>> getAllMbti(){
        return new ResponseEntity<>(mbtiService.getAllMbti(), HttpStatus.OK);
    }

    // 사용자 비선호 mbti 리스트 조회
    @GetMapping("/mbti/{username}")
    public ResponseEntity<List<Mbti>> getUserHateMbtiInfo(@PathVariable("username") String username){
        return new ResponseEntity<>(hateMbtiService.getUserHateMbtis(username), HttpStatus.OK);
    }

    // 사용자 비선호 mbti 생성/업데이트
    @PostMapping("/mbti/{username}")
    public ResponseEntity<?> updateUserHateMbtiInfo(@PathVariable("username") String username, @RequestBody List<Mbti> hateMbtiList){

        return new ResponseEntity<>(hateMbtiService.updateUserHateMbti(username,hateMbtiList), HttpStatus.OK);
    }
}
