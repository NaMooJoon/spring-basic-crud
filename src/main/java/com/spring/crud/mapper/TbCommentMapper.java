package com.spring.crud.mapper;

import com.spring.crud.dto.TbCmtDto.TbCmtAfterSelectDto;
import com.spring.crud.dto.TbCmtDto.TbCmtListDto;
import com.spring.crud.dto.TbCmtDto.TbCmtPagedListDto;
import com.spring.crud.dto.TbCmtDto.TbCmtScrollListDto;
import java.util.List;

public interface TbCommentMapper {
    TbCmtAfterSelectDto detail(String id);

    List<TbCmtAfterSelectDto> getList(TbCmtListDto params);

    List<TbCmtAfterSelectDto> page(TbCmtPagedListDto params);

    List<TbCmtAfterSelectDto> scroll(TbCmtScrollListDto params);

    int pagedCount(TbCmtPagedListDto params);
}
