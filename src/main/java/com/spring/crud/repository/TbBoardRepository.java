package com.spring.crud.repository;

import com.spring.crud.domain.TbBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbBoardRepository extends JpaRepository<TbBoard, String> {
}
