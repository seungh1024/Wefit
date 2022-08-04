package com.ssafy.backend.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.backend.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{

    private final JPAQueryFactory queryFactory;


}
