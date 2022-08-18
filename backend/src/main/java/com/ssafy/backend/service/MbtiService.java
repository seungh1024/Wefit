package com.ssafy.backend.service;

import com.ssafy.backend.entity.Mbti;
import com.ssafy.backend.repository.MbtiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MbtiService {
    private final MbtiRepository mbtiRepository;

    public MbtiService(MbtiRepository mbtiRepository) {
        this.mbtiRepository = mbtiRepository;
    }

    public List<Mbti> getAllMbti(){
        return mbtiRepository.findAll();
    }
}
