package com.spring.crud.mapper;

import com.spring.crud.dto.TbBoardListRequestDto;
import com.spring.crud.dto.TbBoardSelectResponseDto;
import java.util.List;

public interface TbBoardMapper {
    TbBoardSelectResponseDto get(String id);

    List<TbBoardSelectResponseDto> getList(TbBoardListRequestDto params);
}
