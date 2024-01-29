package com.spring.crud.repository;

import com.spring.crud.domain.TbFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbFileRepository extends JpaRepository<TbFile, String> {
}
