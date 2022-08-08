package com.ssafy.backend.service;

import com.ssafy.backend.entity.Authority;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class SocialService {

    public final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SocialService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User signup(String userEmail){
        if(userRepository.findOneWithAuthoritiesByUserEmail(userEmail).orElse(null)!=null){
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .userEmail(userEmail)
                .userPassword(passwordEncoder.encode(userEmail))
                .authorities(Collections.singleton(authority))
                .build();
        return userRepository.save(user);
    }

}
