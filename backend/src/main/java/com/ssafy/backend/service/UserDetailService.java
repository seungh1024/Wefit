package com.ssafy.backend.service;

import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserDetail;
import com.ssafy.backend.repository.UserDetailCustomRepository;
import com.ssafy.backend.repository.UserDetailRepository;
import com.ssafy.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService {

    private final UserDetailRepository userDetailRepository;
    private final UserRepository userRepository;
    private final UserDetailCustomRepository userDetailCustomRepository;


    //유저 디테일 정보 가져오기 R
    public UserDetail getUserDetail(String userEmail){
        User user = userRepository.findUserByUserEmail(userEmail);
        UserDetail userDetail = userDetailRepository.findUserDetailByUser(user);
        System.out.println(userDetail);
//      userDetail.setUser(null);

        return userDetail;
    }

    // 유저 디테일 정보 저장 C
    public UserDetail createUserDetail(String userEmail, UserDetail userDetail){
        User user = userRepository.findUserByUserEmail(userEmail);
        userDetail.setUser(user);
        userDetailRepository.save(userDetail);

        return userDetail;
    }

    // 유저 디테일 정보 업데이트 U
    public Long updateUserDetail(String userEmail, UserDetail userDetail){
        Long result = userDetailCustomRepository.updateUserDetail(userEmail, userDetail);
        return result;
    }

    public void deleteUserDetail(String userEmail){
        userDetailRepository.deleteUserDetailByUserUserEmail(userEmail);
    }

//    public UserDetail updateUserDetail(String userEmail, UserDetailDto userDetailDto){
//        User user = userRepository.findUserByUserEmail(userEmail);
//        if(userRepository.findOneWithAuthoritiesByUserEmail(userDto.getUserEmail()).orElse(null)!=null){
//            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
//        }
//
//    }
}
