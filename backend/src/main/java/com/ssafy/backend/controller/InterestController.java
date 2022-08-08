package com.ssafy.backend.controller;

import com.ssafy.backend.entity.Interest;
import com.ssafy.backend.entity.UserDetail;
import com.ssafy.backend.service.InterestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InterestController {

    private final InterestService interestService;

    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    // 관심사 전체 목록 조회
    @GetMapping("/like")
    public ResponseEntity<List<Interest>> getAllInterest(){
        return new ResponseEntity<>(interestService.getAllInterest(), HttpStatus.OK);
    }

}
