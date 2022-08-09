package com.ssafy.backend.controller;

import com.ssafy.backend.dto.ErrorDto;
import com.ssafy.backend.dto.PasswordDto;
import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.UserRepository;
import com.ssafy.backend.service.MailService;
import com.ssafy.backend.service.UserService;
import com.ssafy.backend.vo.MailVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    private final MailService mailService;

    private final UserRepository userRepository;

    private JavaMailSender mailSender;

    public UserController(UserService userService, MailService mailService, UserRepository userRepository){
        this.userService=userService;
        this.mailService = mailService;
        this.userRepository = userRepository;
    }

    //회원가입 controller
    @PostMapping("/user")
    public ResponseEntity<?> signup(
            @Valid @RequestBody UserDto userDto
    ){
        User user = userService.signup(userDto);
        if(user != null){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.ok(new ErrorDto(200,"이미 가입된 이메일입니다."));
        }
    }

    // 모든 사용자 조회 controller
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<User> getMyUserInfo(){
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    // 해당 사용자 정보 조회
    @GetMapping("/user/{username}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUserInfo(@PathVariable String username){
        User user = userRepository.findUserByUserEmail(username);
        if(user==null){
            return ResponseEntity.ok(new ErrorDto(401,"존재하지 않는 회원입니다"));
        }else{
            return new ResponseEntity<User>(userService.getUserWithAuthorities(username).get(), HttpStatus.OK);
        }

    }

    // 해당 사용자 삭제
//    @DeleteMapping("/user/{username}")




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

    // 비밀번호 확인
    @PostMapping("/password")
    public ResponseEntity<Boolean> checkPassword(@RequestBody PasswordDto passwordDto) {

        return ResponseEntity.ok(passwordDto.getPassword().equals(passwordDto.getPasswordCheck()));
    }

//    비밀번호 찾을 때
//    todo : 수정필요

    @GetMapping("/check/findPw/{email}")
    public @ResponseBody Map<String, Boolean> findPassword(@PathVariable String email){
        Map<String, Boolean> json = new HashMap<>();
        boolean pwfindCheck = userService.userEmailCheck(email);
        json.put("check", pwfindCheck);
        return json;
    }

     // 임시비밀번호 메일 보내기
    @PostMapping("/check/findPw/sendEmail")
    public ResponseEntity<?> sendPasswordEmail(@RequestParam("userEmail") String email){
        try{
            MailVo mailVo = mailService.createMail(email);
            mailService.sendMail(mailVo);
            return new ResponseEntity<String>("이메일이 전송되었습니다",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("이메일 전송 실패",HttpStatus.OK);
        }

    }

}
