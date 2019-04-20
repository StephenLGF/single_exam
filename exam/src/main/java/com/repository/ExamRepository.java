package com.repository;

import com.entity.Exam;
import com.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface ExamRepository extends JpaSpecificationExecutor<Exam>, JpaRepository<Exam, Long> {

    List<Exam> findByUserId(Long userId);
}