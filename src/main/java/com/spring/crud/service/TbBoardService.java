package com.spring.crud.service;

import com.spring.crud.dto.TbBoardCreateRequestDto;
import com.spring.crud.dto.TbBoardCreateResponceDto;

public interface TbBoardService {

    public TbBoardCreateResponceDto create(TbBoardCreateRequestDto params);
}
