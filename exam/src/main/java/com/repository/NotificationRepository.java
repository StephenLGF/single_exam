package com.repository;

import com.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface NotificationRepository extends JpaSpecificationExecutor<Notification>, JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
}