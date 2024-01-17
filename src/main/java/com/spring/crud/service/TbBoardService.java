package com.spring.crud.service;

import com.spring.crud.dto.TbBoardCreateRequestDto;
import com.spring.crud.dto.TbBoardCreateResponseDto;
import com.spring.crud.dto.TbBoardSelectResponseDto;
import com.spring.crud.dto.TbBoardUpdateRequestDto;
import com.spring.crud.dto.TbBoardUpdateResponseDto;

public interface TbBoardService {

    public TbBoardCreateResponseDto create(TbBoardCreateRequestDto params);

    public TbBoardUpdateResponseDto update(TbBoardUpdateRequestDto params);

    public TbBoardSelectResponseDto get(String id);
}
