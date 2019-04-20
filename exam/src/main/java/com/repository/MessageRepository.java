package com.repository;

import com.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface MessageRepository extends JpaSpecificationExecutor<Message>, JpaRepository<Message, Long> {
    Message findById(Long id);

    List<Message> findByIdIn(Set<Long> idSet);
}