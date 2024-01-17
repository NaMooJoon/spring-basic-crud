package com.spring.crud.controller;

import com.spring.crud.dto.TbBoardCreateRequestDto;
import com.spring.crud.dto.TbBoardCreateResponceDto;
import com.spring.crud.service.TbBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "1. 게시판 API 안내",
        description = "게시판 관련 RestfulAPI 정의한 RestController.")
@RequestMapping("/api/board")
@RestController
public class TbBoardRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbBoardService tbBoardService;

    @Autowired
    public TbBoardRestController(TbBoardService tbBoardService) {
        this.tbBoardService = tbBoardService;
    }

    @Operation(summary = "게시판 글 등록",
            description = "게시판 글 신규 등록을 위한 컨트롤러 (접근권한: ALL) <br / >"
                    + "@param TbBoardCreateRequestDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponceEntity\\<TbBoardCreateResponceDto\\> <<br />"
                    + "@exception 중복 <br />")
    @PostMapping("")
    public ResponseEntity<TbBoardCreateResponceDto> save(@RequestBody TbBoardCreateRequestDto params) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbBoardService.create(params));
    }
}
