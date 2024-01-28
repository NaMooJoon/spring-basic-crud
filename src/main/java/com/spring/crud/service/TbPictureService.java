package com.spring.crud.service;

import com.spring.crud.dto.TbPictureDto.TbPictureAfterCreateDto;
import com.spring.crud.dto.TbPictureDto.TbPictureAfterSelectDto;
import com.spring.crud.dto.TbPictureDto.TbPictureAfterUpdateDto;
import com.spring.crud.dto.TbPictureDto.TbPictureCreateDto;
import com.spring.crud.dto.TbPictureDto.TbPictureDeleteDto;
import com.spring.crud.dto.TbPictureDto.TbPictureListDto;
import com.spring.crud.dto.TbPictureDto.TbPictureScrollListDto;
import com.spring.crud.dto.TbPictureDto.TbPictureUpdateDto;
import java.util.List;

public interface TbPictureService {

    TbPictureAfterCreateDto create(TbPictureCreateDto params);

    TbPictureAfterUpdateDto update(TbPictureUpdateDto params);

    TbPictureAfterUpdateDto delete(TbPictureDeleteDto params);

    TbPictureAfterSelectDto detail(String id);

    List<TbPictureAfterSelectDto> list(TbPictureListDto params);

    List<TbPictureAfterSelectDto> moreList(TbPictureScrollListDto params);
}
