package com.spring.crud.service.impl;

import com.spring.crud.domain.TbPicture;
import com.spring.crud.dto.TbPictureDto.TbPictureAfterCreateDto;
import com.spring.crud.dto.TbPictureDto.TbPictureAfterSelectDto;
import com.spring.crud.dto.TbPictureDto.TbPictureAfterUpdateDto;
import com.spring.crud.dto.TbPictureDto.TbPictureCreateDto;
import com.spring.crud.dto.TbPictureDto.TbPictureDeleteDto;
import com.spring.crud.dto.TbPictureDto.TbPictureListDto;
import com.spring.crud.dto.TbPictureDto.TbPictureScrollListDto;
import com.spring.crud.dto.TbPictureDto.TbPictureUpdateDto;
import com.spring.crud.exception.NoMatchedDataException;
import com.spring.crud.mapper.TbPictureMapper;
import com.spring.crud.repository.TbPictureRepository;
import com.spring.crud.service.TbPictureService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbPictureServiceImpl implements TbPictureService {

    TbPictureRepository tbPictureRepository;
    TbPictureMapper tbPictureMapper;

    @Autowired
    public TbPictureServiceImpl(TbPictureRepository tbPictureRepository, TbPictureMapper tbPictureMapper) {
        this.tbPictureRepository = tbPictureRepository;
        this.tbPictureMapper = tbPictureMapper;
    }

    @Override
    public TbPictureAfterCreateDto create(TbPictureCreateDto params) {
        return tbPictureRepository.save(params.toEntity()).toAfterCreateDto();
    }

    @Override
    public TbPictureAfterUpdateDto update(TbPictureUpdateDto params) {
        TbPicture picture = tbPictureRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchedDataException());

        if (params.getTbBoardId() != null) {
            picture.setTbBoardId(params.getTbBoardId());
        }
        if (params.getDeleted() != null) {
            picture.setDeleted(params.getDeleted());
        }
        if (params.getContent() != null) {
            picture.setContent(params.getContent());
        }
        return tbPictureRepository.save(picture).toAfterUpdateDto();
    }

    @Override
    public TbPictureAfterUpdateDto delete(TbPictureDeleteDto params) {
        TbPicture picture = tbPictureRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchedDataException());

        picture.setDeleted("Y");
        return tbPictureRepository.save(picture).toAfterUpdateDto();
    }

    @Override
    public TbPictureAfterSelectDto detail(String id) {
        return tbPictureMapper.detail(id);
    }

    @Override
    public List<TbPictureAfterSelectDto> list(TbPictureListDto params) {
        return tbPictureMapper.getList(params);
    }

    @Override
    public List<TbPictureAfterSelectDto> moreList(TbPictureScrollListDto params) {
        return tbPictureMapper.scroll(params);
    }
}
