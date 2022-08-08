package com.ssafy.backend.repository;

import com.ssafy.backend.entity.UserDetail;

import java.util.Optional;

public interface UserDetailCustomRepository {

    Long updateUserDetail(String userEmail, UserDetail update);

    Long updateUserSingoCount(String username);
}
