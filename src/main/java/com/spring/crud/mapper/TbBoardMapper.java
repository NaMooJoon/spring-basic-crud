package com.spring.crud.mapper;

import com.spring.crud.dto.TbBoardSelectResponseDto;

public interface TbBoardMapper {
    TbBoardSelectResponseDto get(String id);
}
