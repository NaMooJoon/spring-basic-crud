package com.spring.crud.service.impl;

import com.spring.crud.domain.TbFile;
import com.spring.crud.dto.TbFileDto.TbFileAfterCreateDto;
import com.spring.crud.dto.TbFileDto.TbFileAfterSelectDto;
import com.spring.crud.dto.TbFileDto.TbFileAfterUpdateDto;
import com.spring.crud.dto.TbFileDto.TbFileCreateDto;
import com.spring.crud.dto.TbFileDto.TbFileDeleteDto;
import com.spring.crud.dto.TbFileDto.TbFileListDto;
import com.spring.crud.dto.TbFileDto.TbFileScrollListDto;
import com.spring.crud.dto.TbFileDto.TbFileUpdateDto;
import com.spring.crud.exception.NoMatchedDataException;
import com.spring.crud.mapper.TbFileMapper;
import com.spring.crud.repository.TbFileRepository;
import com.spring.crud.service.TbFileService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbFileServiceImpl implements TbFileService {

    TbFileRepository tbFileRepository;
    TbFileMapper tbFileMapper;

    @Autowired
    public TbFileServiceImpl(TbFileRepository tbFileRepository, TbFileMapper tbFileMapper) {
        this.tbFileRepository = tbFileRepository;
        this.tbFileMapper = tbFileMapper;
    }

    @Override
    public TbFileAfterCreateDto create(TbFileCreateDto params) {
        return tbFileRepository.save(params.toEntity()).toAfterCreateDto();
    }

    @Override
    public TbFileAfterUpdateDto update(TbFileUpdateDto params) {
        TbFile file = tbFileRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchedDataException());

        if (params.getTbBoardId() != null) {
            file.setTbBoardId(params.getTbBoardId());
        }
        if (params.getDeleted() != null) {
            file.setDeleted(params.getDeleted());
        }
        if (params.getContent() != null) {
            file.setContent(params.getContent());
        }
        return tbFileRepository.save(file).toAfterUpdateDto();
    }

    @Override
    public TbFileAfterUpdateDto delete(TbFileDeleteDto params) {
        TbFile file = tbFileRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchedDataException());

        file.setDeleted("Y");
        return tbFileRepository.save(file).toAfterUpdateDto();
    }

    @Override
    public TbFileAfterSelectDto detail(String id) {
        return tbFileMapper.detail(id);
    }

    @Override
    public List<TbFileAfterSelectDto> list(TbFileListDto params) {
        return tbFileMapper.getList(params);
    }

    @Override
    public List<TbFileAfterSelectDto> moreList(TbFileScrollListDto params) {
        return tbFileMapper.scroll(params);
    }
}
