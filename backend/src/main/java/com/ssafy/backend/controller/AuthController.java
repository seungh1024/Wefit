package com.ssafy.backend.controller;


import com.ssafy.backend.dto.LoginDto;
import com.ssafy.backend.dto.TokenDto;
import com.ssafy.backend.jwt.JwtFilter;
import com.ssafy.backend.jwt.TokenProvider;
import com.ssafy.backend.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jdk.nashorn.internal.parser.Token;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;
    private final TokenService tokenService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final RedisTemplate<String, String> redisTemplate;


    public AuthController(TokenProvider tokenProvider, TokenService tokenService, AuthenticationManagerBuilder authenticationManagerBuilder, RedisTemplate redisTemplate) {
        this.tokenProvider = tokenProvider;
        this.tokenService = tokenService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        // 0726 refactoring
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(loginDto.getUserEmail(), loginDto.getUserPassword());
//
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        // authentication 얻기
        Authentication authentication = tokenProvider.createAuthenticate(loginDto.getUserEmail(), loginDto.getUserPassword());

        // access token, refresh token 만들기 0726
        String accessToken = tokenService.createAccessToken(authentication);
        String refreshToken = tokenService.createRefreshToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken + " " + refreshToken);

        return new ResponseEntity<>(new TokenDto(accessToken, refreshToken), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/api/v1/re-issue")
    public ResponseEntity<TokenDto> reIssue(@RequestParam("userEmail") String userEmail, @RequestParam("refreshToken") String refreshToken) {
        TokenDto responseDto = tokenService.reIssueAccessToken(userEmail, refreshToken);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/api/v1/logout")
    public ResponseEntity<?> logout(@RequestParam String token) {

//        SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 3. Redis 에서 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제합니다.
        if(redisTemplate.opsForValue().get(authentication.getName()) != null){
            redisTemplate.delete(authentication.getName());
        }
        System.out.println(authentication);
        Date expiration = tokenProvider.getExpiration(token);
        redisTemplate.opsForValue()
                .set(token,"logout",expiration.getTime(), TimeUnit.MILLISECONDS);

//        Long expiration =authentication.get
//        // 4. 해당 Access Token 유효시간 가지고 와서 BlackList 로 저장하기
//        Long expiration = jwtTokenProvider.getExpiration(logout.getAccessToken());
//        redisTemplate.opsForValue()
//                .set(logout.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
