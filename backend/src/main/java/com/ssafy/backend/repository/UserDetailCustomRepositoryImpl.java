package com.ssafy.backend.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.ssafy.backend.entity.QUserDetail;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserDetail;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class UserDetailCustomRepositoryImpl implements UserDetailCustomRepository{

    private final UserRepository userRepository;
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory; // 이놈이 문제

    public UserDetailCustomRepositoryImpl(UserRepository userRepository, EntityManager em) {
        this.userRepository = userRepository;
        this.em = em;
        jpaQueryFactory = new JPAQueryFactory(em);
    }

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
    public Long updateUserDetail(String userEmail, UserDetail update){
        User user = userRepository.findUserByUserEmail(userEmail);
        UserDetail destUserDetail = findUserDetailByUserId(user.getUserId());

        QUserDetail userDetailItem = QUserDetail.userDetail;
        JPAUpdateClause updateClause = new JPAUpdateClause(em, userDetailItem);

        long result = updateClause.where(userDetailItem.userDetailId.eq(destUserDetail.getUserDetailId()))
                .set(userDetailItem.userGender, update.getUserGender())
                .set(userDetailItem.userName, update.getUserName())
                .set(userDetailItem.userNickname, update.getUserNickname())
                .set(userDetailItem.userField, update.getUserField())
                .set(userDetailItem.userPhone, update.getUserPhone())
                .set(userDetailItem.userSingoCount, update.getUserSingoCount())
                .execute();


        return result;
    }

    // 유저 신고
    @Override
    @Transactional
    public Long updateUserSingoCount(String username) {
        User user = userRepository.findUserByUserEmail(username);
        UserDetail userDetail = findUserDetailByUserId(user.getUserId());
        QUserDetail userDetailItem = QUserDetail.userDetail;
        JPAUpdateClause updateClause = new JPAUpdateClause(em, userDetailItem);
        System.out.println( userDetail.getUserSingoCount());
        Long result = updateClause.where(userDetailItem.userDetailId.eq(userDetail.getUserDetailId()))
                .set(userDetailItem.userSingoCount, userDetail.getUserSingoCount()+1)
                .execute();
        return result;
    }

}
