package com.ssafy.backend.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.backend.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HateMbtiCustomRepositoryImpl implements HateMbtiCustomRepository{

    private final HateMbtiRepository hateMbtiRepository;
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public HateMbtiCustomRepositoryImpl(HateMbtiRepository hateMbtiRepository, EntityManager em) {
        this.hateMbtiRepository = hateMbtiRepository;
        this.em = em;
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    public List<Mbti> getUserHateMbtisByUserDetailId(long userDetailId){
        QHateMbti qHateMbti = QHateMbti.hateMbti;
        return jpaQueryFactory
                .select(qHateMbti.mbtiName)
                .from(qHateMbti)
                .where(qHateMbti.userDetail.userDetailId.eq(userDetailId))
                .fetch();
    }

    public Long deleteUserHateMbti(long userDetailId){
        QHateMbti qHateMbti = QHateMbti.hateMbti;
        return jpaQueryFactory
                .delete(qHateMbti)
                .where(qHateMbti.userDetail.userDetailId.eq(userDetailId))
                .execute();
    }

}
