package com.spring.crud.service;

import com.spring.crud.dto.TbUserAfterCreateDto;
import com.spring.crud.dto.TbUserAfterSelectDto;
import com.spring.crud.dto.TbUserAfterUpdateDto;
import com.spring.crud.dto.TbUserCreateDto;
import com.spring.crud.dto.TbUserUpdateDto;

public interface TbUserService {
    TbUserAfterCreateDto create(TbUserCreateDto params);

    TbUserAfterSelectDto get(String id);

    TbUserAfterUpdateDto update(TbUserUpdateDto params);
}
