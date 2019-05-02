package com.repository;

import com.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CollectionRepository extends JpaSpecificationExecutor<Collection>, JpaRepository<Collection, Long> {
    List<Collection> findByUserId(Long userId);

    List<Collection> findByNewsId(Long newsId);
}