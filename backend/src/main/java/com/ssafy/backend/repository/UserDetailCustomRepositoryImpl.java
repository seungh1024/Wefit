package com.ssafy.backend.repository;

import com.querydsl.core.dml.UpdateClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.ssafy.backend.entity.QUserDetail;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class UserDetailCustomRepositoryImpl implements UserDetailCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;
    private final UserRepository userRepository;
    private final EntityManager em;

    // 유저 테이블의 유저 아이디로 유저 디테일 정보 조회
    public UserDetail findUserDetailByUserId(long userId){
        QUserDetail quserDetail = QUserDetail.userDetail;
        return jpaQueryFactory.select(quserDetail)
                .from(quserDetail)
                .where(quserDetail.user.userId.eq(userId))
                .fetchOne();
    }

    // 유저테이블의 유저 이메일로 유저 디테일 조회
    public UserDetail findUserDetailByUserEmail(String userEmail){
        User user = userRepository.findUserByUserEmail(userEmail);
        UserDetail destUserDetail = findUserDetailByUserId(user.getUserId());
        return destUserDetail;
    }

    //
    @Transactional
    public Long updateUserDetail(String userEmail, UserDetail userDetail){
        User user = userRepository.findUserByUserEmail(userEmail);
        UserDetail destUserDetail = findUserDetailByUserId(user.getUserId());

        QUserDetail userDetailItem = QUserDetail.userDetail;
        JPAUpdateClause updateClause = new JPAUpdateClause(em, userDetailItem);

        long result = updateClause.where(userDetailItem.userDetailId.eq(userDetail.getUserDetailId()))
                .set(userDetailItem.userNickname, userDetail.getUserNickname())
                .execute();

        System.out.println("updateUserDetail" + result);

        return result;
    }

}
