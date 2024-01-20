package com.spring.crud.controller;

import com.spring.crud.dto.TbUserAfterCreateDto;
import com.spring.crud.dto.TbUserAfterSelectDto;
import com.spring.crud.dto.TbUserAfterUpdateDto;
import com.spring.crud.dto.TbUserCreateDto;
import com.spring.crud.dto.TbUserUpdateDto;
import com.spring.crud.service.TbUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "1. 회원 API 안내",
        description= "회원 관련 기능 정의한 RestController.")
@RequestMapping("/api/user")
@RestController
public class TbUserRestController {

    private final TbUserService tbUserService;

    @Autowired
    public TbUserRestController(TbUserService tbUserService) {
        this.tbUserService = tbUserService;
    }

    @Operation(summary = "회원 정보 등록",
            description = "회원 신규 정보 등록을 위한 컨트롤러 (누구나 접근 가능) <br />"
                    + "@param TbuserCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbuserAfterCreateDto\\> <br />"
                    + "@exception 중복 <br />")
    @PostMapping("")
    public ResponseEntity<TbUserAfterCreateDto> save(@Valid @RequestBody TbUserCreateDto params) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbUserService.create(params));
    }

    @Operation(summary = "회원 정보 조회",
            description = "아이디를 통해 한명의 회원 정보 조회를 위한 컨트롤러 <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbboardSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />")
    @GetMapping("/{id}")
    public ResponseEntity<TbUserAfterSelectDto> get(@PathVariable("id") String id) {
        TbUserAfterSelectDto tbUserAfterSelectDto = tbUserService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(tbUserAfterSelectDto);
    }

    @PutMapping("")
    public ResponseEntity<TbUserAfterUpdateDto> update(@Valid @RequestBody TbUserUpdateDto params) {
        TbUserAfterUpdateDto tbUserAfterUpdateDto = tbUserService.update(params);
        return ResponseEntity.status(HttpStatus.OK).body(tbUserAfterUpdateDto);
    }
}
