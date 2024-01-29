package com.spring.crud.service;

import com.spring.crud.dto.TbFileDto.TbFileAfterCreateDto;
import com.spring.crud.dto.TbFileDto.TbFileAfterSelectDto;
import com.spring.crud.dto.TbFileDto.TbFileAfterUpdateDto;
import com.spring.crud.dto.TbFileDto.TbFileCreateDto;
import com.spring.crud.dto.TbFileDto.TbFileDeleteDto;
import com.spring.crud.dto.TbFileDto.TbFileListDto;
import com.spring.crud.dto.TbFileDto.TbFileScrollListDto;
import com.spring.crud.dto.TbFileDto.TbFileUpdateDto;
import java.util.List;

public interface TbFileService {

    TbFileAfterCreateDto create(TbFileCreateDto params);

    TbFileAfterUpdateDto update(TbFileUpdateDto params);

    TbFileAfterUpdateDto delete(TbFileDeleteDto params);

    TbFileAfterSelectDto detail(String id);

    List<TbFileAfterSelectDto> list(TbFileListDto params);

    List<TbFileAfterSelectDto> moreList(TbFileScrollListDto params);
}
