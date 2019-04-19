package com.repository;

import com.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuizRepository extends JpaSpecificationExecutor<Quiz>, JpaRepository<Quiz, Long> {
    Quiz findQuizById(Long id);
}