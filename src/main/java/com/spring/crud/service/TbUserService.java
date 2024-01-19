package com.spring.crud.service;

import com.spring.crud.dto.TbUserAfterCreateDto;
import com.spring.crud.dto.TbUserAfterSelectDto;
import com.spring.crud.dto.TbUserCreateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface TbUserService {
    TbUserAfterCreateDto create(TbUserCreateDto params);

    TbUserAfterSelectDto get(String id);
}
