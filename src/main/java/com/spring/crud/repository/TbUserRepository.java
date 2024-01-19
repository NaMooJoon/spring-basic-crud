package com.spring.crud.repository;

import com.spring.crud.domain.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbUserRepository extends JpaRepository<TbUser, String> {
}
