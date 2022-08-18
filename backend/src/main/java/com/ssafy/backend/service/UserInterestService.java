package com.ssafy.backend.service;

import com.ssafy.backend.entity.Interest;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserDetail;
import com.ssafy.backend.entity.UserInterest;
import com.ssafy.backend.repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInterestService {

    private final UserInterestRepository userInterestRepository;
    private final UserRepository userRepository;

    private final UserDetailRepository userDetailRepository;

    private final UserInterestCustomRepositoryImpl userInterestCustomRepositoryimpl;


    public UserInterestService(UserInterestRepository userInterestRepository, UserRepository userRepository, UserDetailRepository userDetailRepository, UserInterestCustomRepositoryImpl userInterestCustomRepositoryimpl) {
        this.userInterestRepository = userInterestRepository;
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
        this.userInterestCustomRepositoryimpl = userInterestCustomRepositoryimpl;
    }

//    public List<UserInterest> getUserInterest(String userEmail){
//        User user = userRepository.findUserByUserEmail(userEmail);
//        UserDetail userDetail = userDetailRepository.findUserDetailByUser(user);
//        List<UserInterest> result = userInterestRepository.findUserInterestByUserDetail(userDetail);
//
//        return result;
//    }

    // 유저 관심사 정보 가져오기
    public List<Interest> getUserInterest(String userEmail){
        User user = userRepository.findUserByUserEmail(userEmail);
        UserDetail userDetail = userDetailRepository.findUserDetailByUser(user);


        return userInterestCustomRepositoryimpl.getUserInterestByUserDetailId(userDetail.getUserDetailId());
    }


    // 유저 관심사 정보 업데이트
    @Transactional
    public Long updateUserInterest(String userEmail, List<Interest> interestList){
        User user = userRepository.findUserByUserEmail(userEmail);
        UserDetail userDetail = userDetailRepository.findUserDetailByUser(user);

        Long result = userInterestCustomRepositoryimpl.deleteUserInterest(userDetail.getUserDetailId());

   //     List<UserInterest> userInterestList = new ArrayList<>();

        for(Interest i : interestList){
            UserInterest temp =new UserInterest();
            temp.setUserDetail(userDetail);
            temp.setInterest(i);
  //          userInterestList.add(temp);
            userInterestRepository.save(temp);
            userInterestRepository.refresh(temp);
        }
//        System.out.println( "뿡뿡뿡");
   //     System.out.println( userInterestList);
  //      System.out.println( userInterestRepository.saveAll(userInterestList));
  //      userInterestRepository.saveAll(userInterestList);
        return result;
    }


}
