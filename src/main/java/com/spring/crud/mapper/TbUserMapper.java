package com.spring.crud.mapper;

import com.spring.crud.dto.TbUserAfterListSelectDto;
import com.spring.crud.dto.TbUserAfterSelectDto;
import com.spring.crud.dto.TbUserListSelectDto;
import java.util.List;

public interface TbUserMapper {
    TbUserAfterSelectDto detail(String id);

    List<TbUserAfterListSelectDto> getList(TbUserListSelectDto params);
}
