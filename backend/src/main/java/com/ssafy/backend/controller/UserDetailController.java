package com.ssafy.backend.controller;

import com.ssafy.backend.dto.ErrorDto;
import com.ssafy.backend.entity.Mbti;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserDetail;
import com.ssafy.backend.repository.UserRepository;
import com.ssafy.backend.service.UserDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserDetailController {
    private final UserDetailService userDetailService;
    private final UserRepository userRepository;
    public UserDetailController(UserDetailService userDetailService, UserRepository userRepository) {
        this.userDetailService = userDetailService;
        this.userRepository = userRepository;
    }

    @GetMapping("/user-info/{username}")
    public ResponseEntity<?> getUserDetailInfo(@PathVariable("username") String username){
        User user = userRepository.findUserByUserEmail(username);
        if(user == null){
            return ResponseEntity.ok(new ErrorDto(401,"존재하지 않는 회원입니다"));
        }else{
            return new ResponseEntity<UserDetail>(userDetailService.getUserDetail(username), HttpStatus.OK);
        }

    }
    @PostMapping("/user-info/{username}")
    public ResponseEntity<?> createUserDetailInfo(@PathVariable("username") String username, @RequestBody UserDetail userDetail){
        User user = userRepository.findUserByUserEmail(username);
        if(user == null){
            return ResponseEntity.ok(new ErrorDto(401,"존재하지 않는 회원입니다"));
        }else{
            return new ResponseEntity<UserDetail>(userDetailService.createUserDetail(username,userDetail), HttpStatus.OK);
        }
    }

    @PutMapping("/user-info/{username}")
    public ResponseEntity<?> updateUserDetailInfo(@PathVariable("username") String username, @RequestBody UserDetail userDetail){
        User user = userRepository.findUserByUserEmail(username);
        if(user == null){
            return ResponseEntity.ok(new ErrorDto(401,"존재하지 않는 회원입니다"));
        }else{
            return new ResponseEntity<>(userDetailService.updateUserDetail(username, userDetail), HttpStatus.OK);
        }

       // return new ResponseEntity<>(userDetailService.updateUserDetail(username, userDetail), HttpStatus.OK);
    }

    @PutMapping("/user-singo/{username}")
    public ResponseEntity<Long> updateUserSingoCount(@PathVariable("username") String username){
        return new ResponseEntity<>(userDetailService.UserSingoCount(username), HttpStatus.OK);
    }

}
