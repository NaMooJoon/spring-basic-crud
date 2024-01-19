package com.spring.crud.mapper;

import com.spring.crud.dto.TbBoardDto.TbBoardListRequestDto;
import com.spring.crud.dto.TbBoardDto.TbBoardSelectResponseDto;
import com.spring.crud.dto.TbBoardDto.TbBoardPagedRequestDto;
import com.spring.crud.dto.TbBoardDto.TbBoardScrollListRequestDto;
import java.util.List;

public interface TbBoardMapper {
    TbBoardSelectResponseDto get(String id);

    List<TbBoardSelectResponseDto> getList(TbBoardListRequestDto params);

    List<TbBoardSelectResponseDto> getPaged(TbBoardPagedRequestDto params);

    Integer getPagedCount(TbBoardPagedRequestDto params);

    List<TbBoardSelectResponseDto> getScroll(TbBoardScrollListRequestDto params);
}
