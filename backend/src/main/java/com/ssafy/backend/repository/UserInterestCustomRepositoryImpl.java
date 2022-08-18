package com.ssafy.backend.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.backend.entity.Interest;
import com.ssafy.backend.entity.QUserInterest;
import com.ssafy.backend.entity.UserInterest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserInterestCustomRepositoryImpl implements UserInterestCustomRepository{

    private final UserRepository userRepository;
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;



    public UserInterestCustomRepositoryImpl(UserRepository userRepository, EntityManager em) {
        this.userRepository = userRepository;
        this.em = em;
        jpaQueryFactory = new JPAQueryFactory(em);

    }

    public List<Interest> getUserInterestByUserDetailId(long userDetailId){
        QUserInterest qUserInterest =QUserInterest.userInterest;
        return jpaQueryFactory
                .select(qUserInterest.interest)
                .from(qUserInterest)
                .where(qUserInterest.userDetail.userDetailId.eq(userDetailId))
                .fetch();
    }


    public Long deleteUserInterest(long userDetailId){
        QUserInterest qUserInterest =QUserInterest.userInterest;
        return jpaQueryFactory
                .delete(qUserInterest)
                .where(qUserInterest.userDetail.userDetailId.eq(userDetailId))
                .execute();
    }

//    public void insertUserInterest(long userDetailId){
//        QUserInterest qUserInterest =QUserInterest.userInterest;
//
//    }

//    public Long updateUserInterest(String userEmail, List<Interest> interestList){
//        QUserInterest qUserInterest =QUserInterest.userInterest;
//        jpaQueryFactory
//                .delete(qUserInterest)
//                .where(qUserInterest.userDetail.user.userEmail.eq(userEmail))
//                .execute();
//
//    }
}
