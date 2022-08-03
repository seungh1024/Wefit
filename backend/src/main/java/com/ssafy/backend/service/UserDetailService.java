package com.ssafy.backend.service;

import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserDetail;
import com.ssafy.backend.repository.UserDetailRepository;
import com.ssafy.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {

    private final UserDetailRepository userDetailRepository;
    private final UserRepository userRepository;

    public UserDetailService(UserDetailRepository userDetailRepository, UserRepository userRepository) {
        this.userDetailRepository = userDetailRepository;
        this.userRepository = userRepository;
    }

    public UserDetail getUserDetail(String userEmail){
        User user = userRepository.findUserByUserEmail(userEmail);
        UserDetail userDetail = userDetailRepository.findUserDetailByUser(user);
        System.out.println(userDetail);
//      userDetail.setUser(null);
        return userDetail;
    }

    public UserDetail createUserDetail(String userEmail, UserDetail userDetail){
        User user = userRepository.findUserByUserEmail(userEmail);
        userDetail.setUser(user);
        userDetailRepository.save(userDetail);

        return userDetail;
    }

//    public UserDetail updateUserDetail(String userEmail, UserDetailDto userDetailDto){
//        User user = userRepository.findUserByUserEmail(userEmail);
//        if(userRepository.findOneWithAuthoritiesByUserEmail(userDto.getUserEmail()).orElse(null)!=null){
//            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
//        }
//
//    }



}
