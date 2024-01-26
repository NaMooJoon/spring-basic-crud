package com.spring.crud.service.impl;

import com.spring.crud.domain.TbComment;
import com.spring.crud.dto.temp.TbCmtAfterCreateDto;
import com.spring.crud.dto.temp.TbCmtAfterSelectDto;
import com.spring.crud.dto.temp.TbCmtAfterUpdateDto;
import com.spring.crud.dto.temp.TbCmtCreateDto;
import com.spring.crud.dto.temp.TbCmtListDto;
import com.spring.crud.dto.temp.TbCmtUpdateDto;
import com.spring.crud.exception.NoMatchedDataException;
import com.spring.crud.mapper.TbCommentMapper;
import com.spring.crud.repository.TbCommentRepository;
import com.spring.crud.service.TbCommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbCommentServiceImpl implements TbCommentService {

    private final TbCommentRepository tbCommentRepository;
    private final TbCommentMapper tbCommentMapper;

    @Autowired
    public TbCommentServiceImpl(TbCommentRepository tbCommentRepository, TbCommentMapper tbCommentMapper) {
        this.tbCommentRepository = tbCommentRepository;
        this.tbCommentMapper = tbCommentMapper;
    }

    @Override
    public TbCmtAfterCreateDto create(TbCmtCreateDto params) {
        return tbCommentRepository.save(params.toEntity()).toAfterCreateDto();
    }

    @Override
    public TbCmtAfterUpdateDto update(TbCmtUpdateDto params) {
        TbComment comment = tbCommentRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchedDataException("TbCommentServiceImpl_ update"));
        System.out.println("======> UPDATE");
        if (params.getTbBoardId() != null) {
            comment.setTbBoardId(params.getTbBoardId());
        }
        if (params.getContent() != null) {
            comment.setContent(params.getContent());
        }
        if (params.getDeleted() != null) {
            comment.setDeleted(params.getDeleted());
        }
        tbCommentRepository.save(comment);
        return comment.toAfterUpdateDto();
    }

    @Override
    public List<TbCmtAfterSelectDto> list(TbCmtListDto params) {
        return tbCommentMapper.getList(params);
    }

}
