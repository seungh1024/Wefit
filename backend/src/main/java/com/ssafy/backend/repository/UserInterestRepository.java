package com.ssafy.backend.repository;

import com.ssafy.backend.entity.UserDetail;
import com.ssafy.backend.entity.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserInterestRepository extends CustomRepository<UserInterest, Integer> {

    List<UserInterest> findUserInterestByUserDetail(UserDetail userDetail);


}
