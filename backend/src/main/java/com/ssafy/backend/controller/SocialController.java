package com.ssafy.backend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.ssafy.backend.dto.TokenDto;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.jwt.JwtFilter;
import com.ssafy.backend.jwt.TokenProvider;
import com.ssafy.backend.radis.RedisService;
import com.ssafy.backend.service.SocialService;
import com.ssafy.backend.service.TokenService;
import com.ssafy.backend.util.RequestUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*")
public class SocialController {

    private final FirebaseAuth firebaseAuth;
    private final SocialService socialService;
    private final TokenProvider tokenProvider;
    private final TokenService tokenService;
    private final RedisService redisService;

    public SocialController(FirebaseAuth firebaseAuth, SocialService socialService, TokenProvider tokenProvider, TokenService tokenService, RedisService redisService) {
        this.firebaseAuth = firebaseAuth;
        this.socialService = socialService;
        this.tokenProvider = tokenProvider;
        this.tokenService = tokenService;
        this.redisService = redisService;
    }

    @PostMapping("/api/v1/social/googleLogin")
    public ResponseEntity<TokenDto> authorize(@RequestHeader("Authorization") String authorization) {
        // TOKEN을 가져온다.
        FirebaseToken decodedToken;
        try {
            String token = RequestUtil.getAuthorizationToken(authorization);
            decodedToken = firebaseAuth.verifyIdToken(token);
        } catch (IllegalArgumentException | FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
        }

        // authentication 얻기
        Authentication authentication = tokenProvider.createAuthenticate(decodedToken.getEmail(), decodedToken.getEmail());
        System.out.println("authorization" + authorization);
        // access token, refresh token 만들기 0726
        String accessToken = tokenService.createAccessToken(authentication);
        String refreshToken = tokenService.createRefreshToken(authentication);

        //userEmail을 key 값으로 refreshToken을 value로 설정
        redisService.setValues(decodedToken.getEmail(), refreshToken);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken + " " + refreshToken);

        return new ResponseEntity<>(new TokenDto(accessToken, refreshToken), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/api/v1/social/googleSignup")
    public ResponseEntity<User> googleSignUp(@RequestHeader("Authorization") String authorization){
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
