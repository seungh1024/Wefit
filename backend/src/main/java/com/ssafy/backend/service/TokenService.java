package com.ssafy.backend.service;

import com.ssafy.backend.dto.TokenDto;
import com.ssafy.backend.entity.Authority;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.jwt.TokenProvider;
import com.ssafy.backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    public TokenService(TokenProvider tokenProvider, UserRepository userRepository){

        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.accessTokenValidityInMilliseconds = 1000L * 60 * 3; //3m
        this.refreshTokenValidityInMilliseconds = 1000L * 60 * 60 * 24 * 14; // 14d
    }

    // AccessToken 생성
    public String createAccessToken(Authentication authentication){
        String userRole = tokenProvider.getAuthorities(authentication);
        return tokenProvider.createToken(authentication.getName(), userRole, this.accessTokenValidityInMilliseconds);
    }

    // RefreshToken 생성
    public String createRefreshToken(Authentication authentication){
        return tokenProvider.createRefreshToken(refreshTokenValidityInMilliseconds);
    }

    public TokenDto reIssueAccessToken(String userEmail, String refreshToken) {
        User user = userRepository.findUserByUserEmail(userEmail);
        tokenProvider.checkRefreshToken(userEmail, refreshToken);

        StringBuilder userRole = new StringBuilder();

        for (Authority a: user.getAuthorities()) {
            userRole.append(a.getAuthorityName());
        }

        String accessToken = tokenProvider.createToken(userEmail, userRole.toString(), this.accessTokenValidityInMilliseconds);
        return new TokenDto(accessToken, refreshToken);
    }
}
