package com.repository;

import com.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface QuizRepository extends JpaSpecificationExecutor<Quiz>, JpaRepository<Quiz, Long> {
    Quiz findQuizById(Long id);

    List<Quiz> findQuizByIdIn(Set<Long> idSet);

    long count();
}