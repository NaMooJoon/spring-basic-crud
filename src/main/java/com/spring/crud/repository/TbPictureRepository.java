package com.spring.crud.repository;

import com.spring.crud.domain.TbPicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbPictureRepository extends JpaRepository<TbPicture, String> {
}
