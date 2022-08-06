package com.ssafy.backend.service;

import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.entity.Authority;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.UserDetailRepository;
import com.ssafy.backend.repository.UserRepository;
import com.ssafy.backend.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserDetailRepository userDetailRepository;
    private final PasswordEncoder passwordEncoder;



    public UserService(UserRepository userRepository, UserDetailRepository userDetailRepository, PasswordEncoder passwordEncoder){

        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User signup(UserDto userDto){
        if(userRepository.findOneWithAuthoritiesByUserEmail(userDto.getUserEmail()).orElse(null)!=null){
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .userEmail(userDto.getUserEmail())
                .userPassword(passwordEncoder.encode(userDto.getUserEmail()))
                .authorities(Collections.singleton(authority))
                .build();

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username){
        return userRepository.findOneWithAuthoritiesByUserEmail(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities(){
        return SecurityUtil.getCurrentUseremail().flatMap(userRepository::findOneWithAuthoritiesByUserEmail);
    }


    public boolean checkEmailDuplicate(String email){
        return userRepository.existsByUserEmail(email);
    }

    public boolean checkNicknameDuplicate(String nickname){
        return userDetailRepository.existsByUserNickname(nickname);
    }


}
