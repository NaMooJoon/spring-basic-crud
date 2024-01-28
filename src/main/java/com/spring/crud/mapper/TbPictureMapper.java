package com.spring.crud.mapper;

import com.spring.crud.dto.TbPictureDto.TbPictureAfterSelectDto;
import com.spring.crud.dto.TbPictureDto.TbPictureListDto;
import com.spring.crud.dto.TbPictureDto.TbPicturePagedListDto;
import com.spring.crud.dto.TbPictureDto.TbPictureScrollListDto;
import java.util.List;

public interface TbPictureMapper {
    TbPictureAfterSelectDto detail(String id);

    List<TbPictureAfterSelectDto> getList(TbPictureListDto params);

    List<TbPictureAfterSelectDto> page(TbPicturePagedListDto params);

    List<TbPictureAfterSelectDto> scroll(TbPictureScrollListDto params);

    int pagedCount(TbPicturePagedListDto params);
}
