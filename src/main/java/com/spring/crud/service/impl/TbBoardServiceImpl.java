package com.spring.crud.service.impl;

import com.spring.crud.domain.TbBoard;
import com.spring.crud.dto.TbBoardDto.*;
import com.spring.crud.dto.TbFileDto.TbFileCreateDto;
import com.spring.crud.dto.TbFileDto.TbFileListDto;
import com.spring.crud.dto.TbPictureDto.TbPictureCreateDto;
import com.spring.crud.dto.TbPictureDto.TbPictureListDto;
import com.spring.crud.dto.common.CommonPagedListResponseDto;
import com.spring.crud.mapper.TbBoardMapper;
import com.spring.crud.repository.TbBoardRepository;
import com.spring.crud.service.TbBoardService;
import com.spring.crud.service.TbFileService;
import com.spring.crud.service.TbPictureService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbBoardServiceImpl implements TbBoardService {

    private final TbPictureService tbPictureService; // TODO: Service가 또 다른 service를 의존하는 것이 객체 지향적인가? => 프로젝트가 복잡해진다면 순환 참조가 될 가능성도 있지 않은가?
    private final TbFileService tbFileService;
    private final TbBoardRepository tbBoardRepository;
    private final TbBoardMapper tbBoardMapper;

    @Autowired
    public TbBoardServiceImpl(TbPictureService tbPictureService, TbFileService tbFileService,
                              TbBoardRepository tbBoardRepository, TbBoardMapper tbBoardMapper) {
        this.tbPictureService = tbPictureService;
        this.tbFileService = tbFileService;
        this.tbBoardRepository = tbBoardRepository;
        this.tbBoardMapper = tbBoardMapper;
    }

    @Override
    public TbBoardCreateResponseDto create(TbBoardCreateRequestDto params) {
        // TbBoard
        TbBoard newBoard = tbBoardRepository.save(params.toEntity());
        // TbPicture
        for (String picture : params.getPictures()) {
            tbPictureService.create(new TbPictureCreateDto(newBoard.getId(), picture));
        }
        // TbFile
        for (String file : params.getFiles()) {
            tbFileService.create(new TbFileCreateDto(newBoard.getId(), file));
        }
        return newBoard.toCreateResponseDto();
    }

    @Override
    public TbBoardUpdateResponseDto update(TbBoardUpdateRequestDto params) {

        TbBoard tbBoard = tbBoardRepository.findById(params.getId())
                .orElseThrow(() -> new EntityNotFoundException(params.getId() + " is not found"));

        if (params.getTitle() != null) {
            tbBoard.setTitle(params.getTitle());
        }
        if (params.getContent() != null) {
            tbBoard.setContent(params.getContent());
        }
        if (params.getDeleted() != null) {
            tbBoard.setDeleted(params.getDeleted());
        }
        tbBoardRepository.save(tbBoard);
        return tbBoard.toUpdateResponseDto();
    }

    @Override
    public TbBoardSelectResponseDto get(String id) {
        TbPictureListDto pic_param = new TbPictureListDto();
        TbFileListDto file_param = new TbFileListDto();

        pic_param.setTbBoardId(id);
        file_param.setTbBoardId(id);

        TbBoardSelectResponseDto result = tbBoardMapper.get(id);
        result.setPictures(tbPictureService.list(pic_param));
        result.setFiles(tbFileService.list(file_param));
        return result;
    }

    @Override
    public List<TbBoardSelectResponseDto> list(TbBoardListRequestDto params) {
        return tbBoardMapper.getList(params);
    }

    @Override
    public CommonPagedListResponseDto<TbBoardSelectResponseDto> pagedList(TbBoardPagedRequestDto params) {
        int listCount = tbBoardMapper.getPagedCount(params);
        int[] arguments = params.afterBuild(listCount);

        return new CommonPagedListResponseDto<>(arguments, tbBoardMapper.getPaged(params));
    }

    @Override
    public List<TbBoardSelectResponseDto> scroll(TbBoardScrollListRequestDto params) {
        return tbBoardMapper.getScroll(params);
    }
}
