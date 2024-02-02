package com.spring.crud.repository;

import com.spring.crud.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleTypeRepository extends JpaRepository<RoleType, String> {
    RoleType findByTypeName(String typeName);
}
