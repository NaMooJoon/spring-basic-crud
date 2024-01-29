package com.spring.crud.mapper;

import com.spring.crud.dto.TbFileDto.TbFileAfterSelectDto;
import com.spring.crud.dto.TbFileDto.TbFileListDto;
import com.spring.crud.dto.TbFileDto.TbFilePagedListDto;
import com.spring.crud.dto.TbFileDto.TbFileScrollListDto;
import java.util.List;

public interface TbFileMapper {
    TbFileAfterSelectDto detail(String id);

    List<TbFileAfterSelectDto> getList(TbFileListDto params);

    List<TbFileAfterSelectDto> page(TbFilePagedListDto params);

    List<TbFileAfterSelectDto> scroll(TbFileScrollListDto params);

    int pagedCount(TbFilePagedListDto params);
}
