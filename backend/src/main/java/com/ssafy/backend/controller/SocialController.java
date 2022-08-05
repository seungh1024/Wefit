package com.ssafy.backend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.ssafy.backend.dto.LoginDto;
import com.ssafy.backend.dto.TokenDto;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.jwt.JwtFilter;
import com.ssafy.backend.service.SocialService;
import com.ssafy.backend.util.RequestUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class SocialController {

    private final FirebaseAuth firebaseAuth;
    private final SocialService socialService;

    public SocialController(FirebaseAuth firebaseAuth, SocialService socialService) {
        this.firebaseAuth = firebaseAuth;
        this.socialService = socialService;
    }

//    @PostMapping("/api/v1/googleLogin")
//    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {
//
//        // authentication 얻기
//        Authentication authentication = tokenProvider.createAuthenticate(loginDto.getUserEmail(), loginDto.getUserPassword());
//
//        // access token, refresh token 만들기 0726
//        String accessToken = tokenService.createAccessToken(authentication);
//        String refreshToken = tokenService.createRefreshToken(authentication);
//
//        //userEmail을 key 값으로 refreshToken을 value로 설정
//        redisService.setValues(loginDto.getUserEmail(),refreshToken);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken + " " + refreshToken);
//
//        return new ResponseEntity<>(new TokenDto(accessToken, refreshToken), httpHeaders, HttpStatus.OK);
//    }

    @PostMapping("/api/v1/googleSignup")
    public ResponseEntity<User> googleSignUp(@RequestHeader("Authorization") String authorization){
        System.out.println(" ----------- googleSignup ------------ ");
        // TOKEN을 가져온다.
        FirebaseToken decodedToken;
        try {
            String token = RequestUtil.getAuthorizationToken(authorization);
            decodedToken = firebaseAuth.verifyIdToken(token);
        } catch (IllegalArgumentException | FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }

        return ResponseEntity.ok(socialService.signup(decodedToken.getEmail()));
    }

}
