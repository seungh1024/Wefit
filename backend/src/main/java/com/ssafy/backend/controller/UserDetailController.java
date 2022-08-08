package com.ssafy.backend.controller;

import com.ssafy.backend.dto.UserDetailDto;
import com.ssafy.backend.entity.UserDetail;
import com.ssafy.backend.service.UserDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserDetailController {
    private final UserDetailService userDetailService;
    public UserDetailController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping("/user-info/{username}")
    public ResponseEntity<UserDetail> getUserDetailInfo(@PathVariable("username") String username){
        return new ResponseEntity<>(userDetailService.getUserDetail(username), HttpStatus.OK);
    }
    @PostMapping("/user-info/{username}")
    public ResponseEntity<UserDetail> createUserDetailInfo(@PathVariable("username") String username, @RequestBody UserDetail userDetail){
        System.out.println(userDetail);
        return new ResponseEntity<>(userDetailService.createUserDetail(username,userDetail), HttpStatus.OK);
    }

    @PutMapping("/user-info/{username}")
    public ResponseEntity<Long> updateUserDetailInfo(@PathVariable("username") String username, @RequestBody UserDetail userDetail){
        return new ResponseEntity<>(userDetailService.updateUserDetail(username, userDetail), HttpStatus.OK);
    }

    @PutMapping("/user-singo/{username}")
    public ResponseEntity<Long> updateUserSingoCount(@PathVariable("username") String username){
        return new ResponseEntity<>(userDetailService.UserSingoCount(username), HttpStatus.OK);
    }


}
