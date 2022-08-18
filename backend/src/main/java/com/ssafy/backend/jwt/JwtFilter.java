package com.ssafy.backend.jwt;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.ssafy.backend.service.CustomUserDetailsService;
import com.ssafy.backend.util.RequestUtil;
import org.apache.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private TokenProvider tokenProvider;
    private final FirebaseAuth firebaseAuth;
    private final CustomUserDetailsService customUserDetailsService;

    // 생성자
    public JwtFilter(TokenProvider tokenProvider, FirebaseAuth firebaseAuth, CustomUserDetailsService customUserDetailsService) {
        this.tokenProvider = tokenProvider;
        this.firebaseAuth = firebaseAuth;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();

        //firebase 인증
        System.out.println(" ------ firebase start ------- ");
        try {
            FirebaseToken decodedToken;
            if (httpServletRequest.getHeader("Authorization") == null){
                throw new Exception();
            }
            String header = RequestUtil.getAuthorizationToken(httpServletRequest.getHeader("Authorization"));

            decodedToken = firebaseAuth.verifyIdToken(header); // 토큰 decode
            System.out.println(decodedToken);

            // TODO: 2022-08-05 이거 문제 해결하기..
//            Authentication authentication = tokenProvider.getAuthentication(decodedToken.toString()); // 토큰의 인증 정보 가져오기
//            SecurityContextHolder.getContext().setAuthentication(authentication); // 토큰 인증 정보를 security context에 저장
            
            chain.doFilter(request, response);
            return;
        } catch (FirebaseAuthException e) {
            System.out.println("httpstatus " + HttpStatus.SC_UNAUTHORIZED);
            System.out.println("error " + e.getMessage());
        } catch (Exception e) {
            System.out.println("no header");
        }

        //local jwt 인증
        System.out.println(" ------ local jwt start ------ ");
        String jwt = resolveToken(httpServletRequest);
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt) && tokenProvider.isNotLogin(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt); // 토큰의 인증 정보 가져오기
            SecurityContextHolder.getContext().setAuthentication(authentication); // 토큰 인증 정보를 security context에 저장
            System.out.println("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {} "+  authentication.getName() + requestURI);
        } else {
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {} " + requestURI);
        }
        chain.doFilter(request, response);
    }

    // 토큰 정보를 가져오는 메소드
    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
