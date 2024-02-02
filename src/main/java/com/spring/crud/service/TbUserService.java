package com.spring.crud.service;

import com.spring.crud.dto.TbUserDto.TbUserAfterCreateDto;
import com.spring.crud.dto.TbUserDto.TbUserAfterListSelectDto;
import com.spring.crud.dto.TbUserDto.TbUserAfterSelectDto;
import com.spring.crud.dto.TbUserDto.TbUserAfterUpdateDto;
import com.spring.crud.dto.TbUserDto.TbUserCreateDto;
import com.spring.crud.dto.TbUserDto.TbUserListSelectDto;
import com.spring.crud.dto.TbUserDto.TbUserUpdateDto;
import java.util.List;

public interface TbUserService {
    TbUserAfterCreateDto signup(TbUserCreateDto params);

    TbUserAfterCreateDto create(TbUserCreateDto params);

    TbUserAfterSelectDto detail(String id);

    TbUserAfterUpdateDto update(TbUserUpdateDto params);

    List<TbUserAfterListSelectDto> list(TbUserListSelectDto params);
}
