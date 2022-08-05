package com.ssafy.backend.repository;

import com.ssafy.backend.entity.UserInterest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class CustomRepositoryImpl<UserInterest, Long extends Serializable> extends SimpleJpaRepository<UserInterest, Long>
    implements CustomRepository<UserInterest, Long> {

  private final EntityManager entityManager;

  public CustomRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void refresh(UserInterest userInterest) {
    entityManager.refresh(userInterest);
  }
}