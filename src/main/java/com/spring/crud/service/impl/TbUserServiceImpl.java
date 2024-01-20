package com.spring.crud.service.impl;

import com.spring.crud.domain.TbUser;
import com.spring.crud.dto.TbUserAfterCreateDto;
import com.spring.crud.dto.TbUserAfterSelectDto;
import com.spring.crud.dto.TbUserAfterUpdateDto;
import com.spring.crud.dto.TbUserCreateDto;
import com.spring.crud.dto.TbUserUpdateDto;
import com.spring.crud.mapper.TbUserMapper;
import com.spring.crud.repository.TbUserRepository;
import com.spring.crud.service.TbUserService;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public TbUserAfterUpdateDto update(TbUserUpdateDto params) {
        TbUser targetUser = tbUserRepository.findById(params.getId())
                .orElseThrow(() -> new EntityNotFoundException(params.getId() + " is not found"));

        if (params.getImage() != null) {
            targetUser.setImage(params.getImage());
        }
        if (params.getUid() != null) {
            targetUser.setUid(params.getUid());
        }
        if (params.getNick() != null) {
            targetUser.setNick(params.getNick());
        }
        if (params.getName() != null) {
            targetUser.setName(params.getName());
        }
        if (params.getPhone() != null) {
            targetUser.setPhone(params.getPhone());
        }
        tbUserRepository.save(targetUser);
        return targetUser.toAfterUpdateDto();
    }
}
