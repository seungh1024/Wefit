package com.ssafy.backend.repository;

import com.ssafy.backend.entity.Mbti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MbtiRepository extends JpaRepository<Mbti,String> {

    @Override
    List<Mbti> findAll();
}
