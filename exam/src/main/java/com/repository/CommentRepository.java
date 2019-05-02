package com.repository;

import com.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CommentRepository extends JpaSpecificationExecutor<Comment>, JpaRepository<Comment, Long> {

    List<Comment> findByNewsId(Long newsId);

}