package com.cognizant.orm_learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cognizant.orm_learn.model.Attempt;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    @Query("SELECT a FROM Attempt a " +
           "LEFT JOIN FETCH a.user u " +
           "LEFT JOIN FETCH a.attemptQuestions aq " +
           "LEFT JOIN FETCH aq.question q " +
           "LEFT JOIN FETCH q.optionsList o " +
           "LEFT JOIN FETCH aq.attemptOptions ao " +
           "LEFT JOIN FETCH ao.options " +
           "WHERE u.id = :userId AND a.id = :attemptId")
    Attempt getAttemptDetail(@Param("userId") int userId, @Param("attemptId") int attemptId);
}
