package com.ssafy.backend.service;

import com.ssafy.backend.entity.Interest;
import com.ssafy.backend.repository.InterestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestService {

    private final InterestRepository interestRepository;

    public InterestService(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public List<Interest> getAllInterest(){
        return interestRepository.findAll();
    }
}
