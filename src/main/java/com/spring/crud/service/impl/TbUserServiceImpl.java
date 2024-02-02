package com.spring.crud.service.impl;

import com.spring.crud.domain.RoleType;
import com.spring.crud.domain.TbUser;
import com.spring.crud.domain.TbUserRoleType;
import com.spring.crud.dto.TbUserDto.TbUserAfterCreateDto;
import com.spring.crud.dto.TbUserDto.TbUserAfterListSelectDto;
import com.spring.crud.dto.TbUserDto.TbUserAfterSelectDto;
import com.spring.crud.dto.TbUserDto.TbUserAfterUpdateDto;
import com.spring.crud.dto.TbUserDto.TbUserCreateDto;
import com.spring.crud.dto.TbUserDto.TbUserListSelectDto;
import com.spring.crud.dto.TbUserDto.TbUserUpdateDto;
import com.spring.crud.exception.AlreadyExistDataException;
import com.spring.crud.exception.NoMatchedDataException;
import com.spring.crud.mapper.TbUserMapper;
import com.spring.crud.repository.RoleTypeRepository;
import com.spring.crud.repository.TbUserRepository;
import com.spring.crud.repository.TbUserRoleTypeRepository;
import com.spring.crud.service.TbUserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TbUserServiceImpl implements TbUserService {

    private final TbUserRepository tbUserRepository;
    private final RoleTypeRepository roleTypeRepository;
    private final TbUserRoleTypeRepository tbUserRoleTypeRepository;
    private final TbUserMapper tbUserMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public TbUserServiceImpl(TbUserRepository tbUserRepository, RoleTypeRepository roleTypeRepository,
                             TbUserRoleTypeRepository tbUserRoleTypeRepository, TbUserMapper tbUserMapper,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.tbUserRepository = tbUserRepository;
        this.roleTypeRepository = roleTypeRepository;
        this.tbUserRoleTypeRepository = tbUserRoleTypeRepository;
        this.tbUserMapper = tbUserMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public TbUserAfterCreateDto signup(TbUserCreateDto params) {
        // check duplicated user
        TbUser user = tbUserRepository.findByUsername(params.getUsername());
        if (user != null) {
            throw new AlreadyExistDataException("Username already exist!");
        }

        String nick = "User" + UUID.randomUUID().toString().substring(0, 4);
        params.setNick(nick);
        params.setJoinFrom("DIRECT");
        params.setProcess("0");
        return create(params);
    }

    @Override
    public TbUserAfterCreateDto create(TbUserCreateDto params) {
        // Password 암호화
        String rawPassword = params.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        params.setPassword(encPassword);

        TbUser savedUser = tbUserRepository.save(params.toEntity());
        // TODO: 권한은 따로 설정해주어야 함.
        RoleType roleType = roleTypeRepository.findByTypeName("ROLE_USER");
        TbUserRoleType tbUserRoleType = TbUserRoleType.of(savedUser, roleType);
        tbUserRoleTypeRepository.save(tbUserRoleType);

        return savedUser.toAfterCreateDto();
    }

    @Override
    public TbUserAfterSelectDto detail(String id) {
        return tbUserMapper.detail(id);
    }

    @Override
    public TbUserAfterUpdateDto update(TbUserUpdateDto params) {
        TbUser targetUser = tbUserRepository.findById(params.getId())
                .orElseThrow(() -> new NoMatchedDataException(params.getId() + " is not found"));

        if (params.getPassword() != null) {
            targetUser.setPassword(params.getPassword());
        }
        if (params.getNick() != null) {
            targetUser.setNick(params.getNick());
        }
        if (params.getName() != null) {
            targetUser.setName(params.getName());
        }
        if (params.getJoinFrom() != null) {
            targetUser.setJoinFrom(params.getJoinFrom());
        }
        if (params.getImage() != null) {
            targetUser.setImage(params.getImage());
        }
        if (params.getPhone() != null) {
            targetUser.setPhone(params.getPhone());
        }
        if (params.getDeleted() != null) {
            targetUser.setDeleted(params.getDeleted());
        }
        if (params.getProcess() != null) {
            targetUser.setProcess(params.getProcess());
        }
        tbUserRepository.save(targetUser);
        return targetUser.toAfterUpdateDto();
    }

    @Override
    public List<TbUserAfterListSelectDto> list(TbUserListSelectDto params) {
        return tbUserMapper.getList(params);
    }
}
