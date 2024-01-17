package com.spring.crud.service;

import com.spring.crud.domain.TbBoard;
import com.spring.crud.dto.TbBoardCreateRequestDto;
import com.spring.crud.dto.TbBoardCreateResponceDto;
import com.spring.crud.repository.TbBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbBoardServiceImpl implements TbBoardService {

    private final TbBoardRepository tbBoardRepository;

    @Autowired
    public TbBoardServiceImpl(TbBoardRepository tbBoardRepository) {
        this.tbBoardRepository = tbBoardRepository;
    }

    @Override
    public TbBoardCreateResponceDto create(TbBoardCreateRequestDto params) {
        TbBoard newBoard = tbBoardRepository.save(params.toEntity());
        return newBoard.toResponceDto();
    }
}
