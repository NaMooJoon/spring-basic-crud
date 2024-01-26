package com.spring.crud.repository;

import com.spring.crud.domain.TbComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbCommentRepository extends JpaRepository<TbComment, String> {
}
