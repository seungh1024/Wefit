package com.ssafy.backend.repository;

import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserDetail;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    UserDetail findUserDetailByUser(User user);
    void deleteUserDetailByUserUserEmail(String userEmail);
    boolean existsByUserNickname(String nickname);

}
