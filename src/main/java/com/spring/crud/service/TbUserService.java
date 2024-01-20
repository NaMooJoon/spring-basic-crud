package com.spring.crud.service;

import com.spring.crud.dto.TbUserAfterCreateDto;
import com.spring.crud.dto.TbUserAfterListSelectDto;
import com.spring.crud.dto.TbUserAfterSelectDto;
import com.spring.crud.dto.TbUserAfterUpdateDto;
import com.spring.crud.dto.TbUserCreateDto;
import com.spring.crud.dto.TbUserListSelectDto;
import com.spring.crud.dto.TbUserUpdateDto;
import java.util.List;

public interface TbUserService {
    TbUserAfterCreateDto create(TbUserCreateDto params);

    TbUserAfterSelectDto get(String id);

    TbUserAfterUpdateDto update(TbUserUpdateDto params);

    List<TbUserAfterListSelectDto> list(TbUserListSelectDto params);
}
