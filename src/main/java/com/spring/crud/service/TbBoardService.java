package com.spring.crud.service;

import com.spring.crud.dto.TbBoardDto.TbBoardCreateRequestDto;
import com.spring.crud.dto.TbBoardDto.TbBoardCreateResponseDto;
import com.spring.crud.dto.TbBoardDto.TbBoardListRequestDto;
import com.spring.crud.dto.TbBoardDto.TbBoardSelectResponseDto;
import com.spring.crud.dto.TbBoardDto.TbBoardUpdateRequestDto;
import com.spring.crud.dto.TbBoardDto.TbBoardUpdateResponseDto;
import com.spring.crud.dto.TbBoardDto.TbBoardPagedRequestDto;
import com.spring.crud.dto.TbBoardScrollListRequestDto;
import com.spring.crud.dto.common.CommonPagedListResponseDto;
import java.util.List;

public interface TbBoardService {

    TbBoardCreateResponseDto create(TbBoardCreateRequestDto params);

    TbBoardUpdateResponseDto update(TbBoardUpdateRequestDto params);

    TbBoardSelectResponseDto get(String id);

    List<TbBoardSelectResponseDto> list(TbBoardListRequestDto params);

    CommonPagedListResponseDto<TbBoardSelectResponseDto> pagedList(TbBoardPagedRequestDto params);

    List<TbBoardSelectResponseDto> scroll(TbBoardScrollListRequestDto params);
}
