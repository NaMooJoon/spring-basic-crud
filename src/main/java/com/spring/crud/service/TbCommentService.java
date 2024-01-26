package com.spring.crud.service;

import com.spring.crud.dto.temp.TbCmtAfterCreateDto;
import com.spring.crud.dto.temp.TbCmtAfterSelectDto;
import com.spring.crud.dto.temp.TbCmtAfterUpdateDto;
import com.spring.crud.dto.temp.TbCmtCreateDto;
import com.spring.crud.dto.temp.TbCmtListDto;
import com.spring.crud.dto.temp.TbCmtUpdateDto;
import java.util.List;

public interface TbCommentService {

    TbCmtAfterCreateDto create(TbCmtCreateDto params);

    TbCmtAfterUpdateDto update(TbCmtUpdateDto params);

    List<TbCmtAfterSelectDto> list(TbCmtListDto params);
}
