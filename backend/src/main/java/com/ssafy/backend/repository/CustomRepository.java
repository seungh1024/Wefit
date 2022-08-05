package com.ssafy.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CustomRepository<UserInterest, Long extends Serializable> extends JpaRepository<UserInterest, Long> {
    void refresh(UserInterest userInterest);
}