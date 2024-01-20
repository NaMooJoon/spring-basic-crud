package com.spring.crud.service.impl;

import com.spring.crud.domain.TbBoard;
import com.spring.crud.dto.TbBoardDto.TbBoardCreateRequestDto;
import com.spring.crud.dto.TbBoardDto.TbBoardCreateResponseDto;
import com.spring.crud.dto.TbBoardDto.TbBoardListRequestDto;
import com.spring.crud.dto.TbBoardDto.TbBoardSelectResponseDto;
import com.spring.crud.dto.TbBoardDto.TbBoardUpdateRequestDto;
import com.spring.crud.dto.TbBoardDto.TbBoardUpdateResponseDto;
import com.spring.crud.dto.TbBoardDto.TbBoardPagedRequestDto;
import com.spring.crud.dto.TbBoardDto.TbBoardScrollListRequestDto;
import com.spring.crud.dto.common.CommonPagedListResponseDto;
import com.spring.crud.mapper.TbBoardMapper;
import com.spring.crud.repository.TbBoardRepository;
import com.spring.crud.service.TbBoardService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbBoardServiceImpl implements TbBoardService {

    private final TbBoardRepository tbBoardRepository;
    private final TbBoardMapper tbBoardMapper;

    @Autowired
    public TbBoardServiceImpl(TbBoardRepository tbBoardRepository, TbBoardMapper tbBoardMapper) {
        this.tbBoardRepository = tbBoardRepository;
        this.tbBoardMapper = tbBoardMapper;
    }

    @Override
    public TbBoardCreateResponseDto create(TbBoardCreateRequestDto params) {
        TbBoard newBoard = tbBoardRepository.save(params.toEntity());
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
        return tbBoardMapper.get(id);
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
        System.out.println("\n========> " + params.getSearchDate());
        return tbBoardMapper.getScroll(params);
    }
}