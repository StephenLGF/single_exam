package com.repository;

import com.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface ProviderRepository extends JpaSpecificationExecutor<Provider>, JpaRepository<Provider, Long> {
    List<Provider> findByIdIn(Set<Long> idSet);
}