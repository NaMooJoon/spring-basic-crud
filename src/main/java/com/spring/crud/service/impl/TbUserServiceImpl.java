package com.spring.crud.service.impl;

import com.spring.crud.domain.TbUser;
import com.spring.crud.dto.TbUserAfterCreateDto;
import com.spring.crud.dto.TbUserAfterSelectDto;
import com.spring.crud.dto.TbUserCreateDto;
import com.spring.crud.mapper.TbUserMapper;
import com.spring.crud.repository.TbUserRepository;
import com.spring.crud.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbUserServiceImpl implements TbUserService {

    private final TbUserRepository tbUserRepository;
    private final TbUserMapper tbUserMapper;

    @Autowired
    public TbUserServiceImpl(TbUserRepository tbUserRepository, TbUserMapper tbUserMapper) {
        this.tbUserRepository = tbUserRepository;
        this.tbUserMapper = tbUserMapper;
    }

    @Override
    public TbUserAfterCreateDto create(TbUserCreateDto params) {
        TbUser savedUser = tbUserRepository.save(params.toEntity());
        return savedUser.toAfterCreateDto();
    }

    @Override
    public TbUserAfterSelectDto get(String id) {
        return tbUserMapper.detail(id);
    }
}
