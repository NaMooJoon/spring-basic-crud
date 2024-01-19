package com.spring.crud.mapper;

import com.spring.crud.dto.TbUserAfterSelectDto;

public interface TbUserMapper {
    TbUserAfterSelectDto detail(String id);
}
