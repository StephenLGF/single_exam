package com.repository;

import com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaSpecificationExecutor<User>, JpaRepository<User, Long> {
    User findById(Long id);

    User findByTelNum(Long telNum);

    List<User> findByIdIn(Set<Long> ids);
}