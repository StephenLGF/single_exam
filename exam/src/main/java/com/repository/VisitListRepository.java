package com.repository;

import com.entity.VisitList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface VisitListRepository extends JpaSpecificationExecutor<VisitList>, JpaRepository<VisitList, Long> {
    List<VisitList> findByUserId(Long userId);

    List<VisitList> findByUserIdAndNewsId(Long userId, Long newsId);

    VisitList findById(Long id);

    VisitList save(VisitList visitList);
}