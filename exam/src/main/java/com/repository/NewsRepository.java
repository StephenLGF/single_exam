package com.repository;

import com.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface NewsRepository extends JpaSpecificationExecutor<News>, JpaRepository<News, Long> {


    List<News> findAll();

    List<News> findByType(Integer type);

    List<News> findByIdIn(Set<Long> idSet);
}