package com.spring.crud.mapper;

import com.spring.crud.dto.TbUserDto.TbUserAfterListSelectDto;
import com.spring.crud.dto.TbUserDto.TbUserAfterSelectDto;
import com.spring.crud.dto.TbUserDto.TbUserListSelectDto;
import java.util.List;

public interface TbUserMapper {
    TbUserAfterSelectDto detail(String id);

    List<TbUserAfterListSelectDto> getList(TbUserListSelectDto params);
}
