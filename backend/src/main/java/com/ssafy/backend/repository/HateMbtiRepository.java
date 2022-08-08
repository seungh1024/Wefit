package com.ssafy.backend.repository;

import com.ssafy.backend.entity.HateMbti;
import com.ssafy.backend.entity.UserDetail;

import java.util.List;

public interface HateMbtiRepository extends CustomRepository<HateMbti, Integer> {

    List<HateMbti> findHateMbtisByUserDetail(UserDetail userDetail);

}
