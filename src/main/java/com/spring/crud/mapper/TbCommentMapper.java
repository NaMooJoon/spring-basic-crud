package com.spring.crud.mapper;

import com.spring.crud.dto.temp.TbCmtAfterSelectDto;
import com.spring.crud.dto.temp.TbCmtListDto;
import com.spring.crud.dto.temp.TbCmtPagedListDto;
import com.spring.crud.dto.temp.TbCmtScrollListDto;
import java.util.List;

public interface TbCommentMapper {
    TbCmtAfterSelectDto detail(String id);

    List<TbCmtAfterSelectDto> getList(TbCmtListDto params);

    List<TbCmtAfterSelectDto> page(TbCmtPagedListDto params);

    List<TbCmtAfterSelectDto> scroll(TbCmtScrollListDto params);

    int pagedCount(TbCmtPagedListDto params);
}
