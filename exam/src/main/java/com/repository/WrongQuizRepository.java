package com.repository;

import com.entity.WrongQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface WrongQuizRepository extends JpaSpecificationExecutor<WrongQuiz>, JpaRepository<WrongQuiz, Long> {
    List<WrongQuiz> findWrongQuizByUserId(Long id);
}