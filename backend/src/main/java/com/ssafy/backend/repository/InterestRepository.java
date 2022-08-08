package com.ssafy.backend.repository;

import com.ssafy.backend.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<Interest, String> {
    @Override
    List<Interest> findAll();
}
