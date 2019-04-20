package com.repository;

import com.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface PaperRepository extends JpaSpecificationExecutor<Paper>, JpaRepository<Paper, Long> {

    List<Paper> findByIdIn(Set<Long> ids);

    Paper findById(Long id);
}