package com.spring.crud.mapper;

import com.spring.crud.dto.TbBoardDto.TbBoardListRequestDto;
import com.spring.crud.dto.TbBoardDto.TbBoardSelectResponseDto;
import com.spring.crud.dto.TbBoardPagedRequestDto;
import java.util.List;

public interface TbBoardMapper {
    TbBoardSelectResponseDto get(String id);

    List<TbBoardSelectResponseDto> getList(TbBoardListRequestDto params);

    List<TbBoardSelectResponseDto> getPaged(TbBoardPagedRequestDto params);

    Integer getPagedCount(TbBoardPagedRequestDto params);
}
