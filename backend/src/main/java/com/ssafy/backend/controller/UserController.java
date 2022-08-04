package com.ssafy.backend.controller;

import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    //회원가입 controller
    @PostMapping("/user")
    public ResponseEntity<User> signup(
            @Valid @RequestBody UserDto userDto
    ){
        return ResponseEntity.ok(userService.signup(userDto));
    }

    // 모든 사용자 조회 controller
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<User> getMyUserInfo(){
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    // 해당 사용자 정보 조회
    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
    }

    // 이메일 중복 확인
    @GetMapping("/email/{email}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        return ResponseEntity.ok(userService.checkEmailDuplicate(email));

    }

    // 닉네임 중복 확인
    @GetMapping("/user-nickname/{nickname}/exists")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@PathVariable String nickname) {
        return ResponseEntity.ok(userService.checkNicknameDuplicate(nickname));

    }
}
