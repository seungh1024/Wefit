package com.ssafy.backend.service;

import com.ssafy.backend.entity.HateMbti;
import com.ssafy.backend.entity.Mbti;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserDetail;
import com.ssafy.backend.repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class HateMbtiService {

    private final UserDetailRepository userDetailRepository;
    private final UserRepository userRepository;
    private final MbtiRepository mbtiRepository;
    private final HateMbtiRepository hatembtiRepository;
    private final HateMbtiCustomRepositoryImpl hateMbtiCustomRepositoryImpl;

    public HateMbtiService(UserDetailRepository userDetailRepository, UserRepository userRepository, MbtiRepository mbtiRepository, HateMbtiCustomRepositoryImpl hateMbtiCustomRepositoryImpl, HateMbtiRepository hatembtiRepository) {
        this.userDetailRepository = userDetailRepository;
        this.userRepository = userRepository;
        this.mbtiRepository = mbtiRepository;
        this.hatembtiRepository = hatembtiRepository;
        this.hateMbtiCustomRepositoryImpl = hateMbtiCustomRepositoryImpl;
    }

    // 유저 비선호 mbti 정보 가져오기
    public List<Mbti> getUserHateMbtis(String userEmail){
        User user = userRepository.findUserByUserEmail(userEmail);
        UserDetail userDetail = userDetailRepository.findUserDetailByUser(user);
        return hateMbtiCustomRepositoryImpl.getUserHateMbtisByUserDetailId(userDetail.getUserDetailId());

    }

    @Transactional
    public Object updateUserHateMbti(String userEmail, List<Mbti> hateMbtiList) {
        User user = userRepository.findUserByUserEmail(userEmail);
        UserDetail userDetail = userDetailRepository.findUserDetailByUser(user);

        Long result = hateMbtiCustomRepositoryImpl.deleteUserHateMbti(userDetail.getUserDetailId());
     //   List<HateMbti> userHateMbtiList = new ArrayList<>();
        for(Mbti m : hateMbtiList){

            HateMbti temp = new HateMbti();
            temp.setUserDetail(userDetail);
            temp.setMbtiName(m);
          //  userHateMbtiList.add(temp);
            hatembtiRepository.save(temp);
            hatembtiRepository.refresh(temp);
        }

        return result;

    }
}
