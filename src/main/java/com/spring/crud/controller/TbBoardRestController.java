package com.spring.crud.controller;

import com.spring.crud.dto.TbBoardDto.*;
import com.spring.crud.dto.TbBoardDto.TbBoardPagedRequestDto;
import com.spring.crud.dto.TbBoardDto.TbBoardScrollListRequestDto;
import com.spring.crud.dto.common.CommonPagedListResponseDto;
import com.spring.crud.service.TbBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbBoardCreateResponseDto\\> <<br />"
                    + "@exception 중복 <br />")
    @PostMapping("")
    public ResponseEntity<TbBoardCreateResponseDto> save(@RequestBody TbBoardCreateRequestDto params, HttpServletRequest request) {
        logger.info(">>>> " + request.getAttribute("test_req"));
        return ResponseEntity.status(HttpStatus.CREATED).body(tbBoardService.create(params));
    }

    @Operation(summary = "게시판 글 수정",
            description = "기존에 존재하는 게시판 글을 수정하기 위한 컨트롤러 (접근권한: ALL) <br / >"
                    + "@param TbBoardCreateRequestDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbBoardUpdateResponseDto\\> <<br />"
                    + "@exception 중복 <br />")
    @PutMapping("")
    public ResponseEntity<TbBoardUpdateResponseDto> update(@RequestBody TbBoardUpdateRequestDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbBoardService.update(params));
    }

    @Operation(summary = "게시 글 하나 조회",
            description = "아이디를 통해 하나의 정보 조회를 위한 컨트롤러 <br />"
                    + "@param id(PathVariable) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbboardSelectDto\\> <br />"
                    + "@exception 정보 없음 <br />")
    @GetMapping("/{id}")
    public ResponseEntity<TbBoardSelectResponseDto> get(@PathVariable("id") String id) {
        TbBoardSelectResponseDto tbBoardSelectResponseDto = tbBoardService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(tbBoardSelectResponseDto);
    }

    @Operation(summary = "게시 글 전체 목록 조회 (검색 기능 포함)",
            description = "게시판 글 전체 목록 조회를 위한 컨트롤러 <br />"
                    + "@param (no parameter) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbboardSelectDto\\> <br />"
                    + "@exception (no exception) <br />")
    @PostMapping("/list")
    public ResponseEntity<List<TbBoardSelectResponseDto>> list(@RequestBody TbBoardListRequestDto params) {
        List<TbBoardSelectResponseDto> tbBoardSelectResponseDtoList = tbBoardService.list(params);
        return ResponseEntity.status(HttpStatus.OK).body(tbBoardSelectResponseDtoList);
    }

    @Operation(summary = "게시 글 목록 페이징 처리 (검색 기능 포함)",
            description = "게시판 글 전체 목록을 페이징 처리 한 글 조회를 위한 컨트롤러 <br />"
                    + "@param (TbBoardPagedRequestDto) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<CommonPagedListResponseDto<TbBoardSelectResponseDto>\\> <br />"
                    + "@exception (no exception) <br />")
    @PostMapping("/page")
    public ResponseEntity<CommonPagedListResponseDto<TbBoardSelectResponseDto>> pagedList(@RequestBody TbBoardPagedRequestDto params) {
        CommonPagedListResponseDto<TbBoardSelectResponseDto> responseBody = tbBoardService.pagedList(params);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @Operation(summary = "게시 글 목록 페이징 처리 (검색 기능 포함)",
            description = "게시판 글 전체 목록을 페이징 처리 한 글 조회를 위한 컨트롤러 <br />"
                    + "@param (no parameter) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbboardSelectDto\\> <br />"
                    + "@exception (no exception) <br />")
    @PostMapping("/scroll")
    public ResponseEntity<List<TbBoardSelectResponseDto>> scroll(@RequestBody TbBoardScrollListRequestDto params) {
        List<TbBoardSelectResponseDto> tbBoardSelectResponseDtoList = tbBoardService.scroll(params);
        return ResponseEntity.status(HttpStatus.OK).body(tbBoardSelectResponseDtoList);
    }
}
