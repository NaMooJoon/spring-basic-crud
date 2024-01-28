package com.spring.crud.service;

import com.spring.crud.dto.TbCmtDto.TbCmtAfterCreateDto;
import com.spring.crud.dto.TbCmtDto.TbCmtAfterSelectDto;
import com.spring.crud.dto.TbCmtDto.TbCmtAfterUpdateDto;
import com.spring.crud.dto.TbCmtDto.TbCmtCreateDto;
import com.spring.crud.dto.TbCmtDto.TbCmtDeleteDto;
import com.spring.crud.dto.TbCmtDto.TbCmtListDto;
import com.spring.crud.dto.TbCmtDto.TbCmtUpdateDto;
import java.util.List;

public interface TbCommentService {

    TbCmtAfterCreateDto create(TbCmtCreateDto params);

    TbCmtAfterUpdateDto update(TbCmtUpdateDto params);

    TbCmtAfterUpdateDto delete(TbCmtDeleteDto params);

    List<TbCmtAfterSelectDto> list(TbCmtListDto params);
}
