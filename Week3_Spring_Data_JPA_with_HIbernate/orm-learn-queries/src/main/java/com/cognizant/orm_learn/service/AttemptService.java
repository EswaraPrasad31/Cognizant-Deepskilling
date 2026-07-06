package com.cognizant.orm_learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.orm_learn.model.Attempt;
import com.cognizant.orm_learn.repository.AttemptRepository;

@Service
public class AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    @Transactional(readOnly = true)
    public Attempt getAttemptDetail(int userId, int attemptId) {
        return attemptRepository.getAttemptDetail(userId, attemptId);
    }
}
