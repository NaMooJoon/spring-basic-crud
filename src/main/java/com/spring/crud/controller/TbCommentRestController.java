package com.spring.crud.controller;

import com.spring.crud.dto.TbCmtDto.TbCmtAfterCreateDto;
import com.spring.crud.dto.TbCmtDto.TbCmtAfterSelectDto;
import com.spring.crud.dto.TbCmtDto.TbCmtAfterUpdateDto;
import com.spring.crud.dto.TbCmtDto.TbCmtCreateDto;
import com.spring.crud.dto.TbCmtDto.TbCmtDeleteDto;
import com.spring.crud.dto.TbCmtDto.TbCmtListDto;
import com.spring.crud.dto.TbCmtDto.TbCmtUpdateDto;
import com.spring.crud.security.PrincipalDetails;
import com.spring.crud.service.TbCommentService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/cmt")
@Controller
public class TbCommentRestController {

    TbCommentService tbCommentService;

    @Autowired
    public TbCommentRestController(TbCommentService tbCommentService) {
        this.tbCommentService = tbCommentService;
    }

    @Operation(summary = "댓글 등록",
            description = "게시판안에 댓글 신규 등록을 위한 컨트롤러 (접근권한: ALL) <br / >"
                    + "@param TbCmtCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbCmtAfterCreateDto\\> <<br />"
                    + "@exception 중복 <br />")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<TbCmtAfterCreateDto> create(@RequestBody TbCmtCreateDto params, @AuthenticationPrincipal
                                                      PrincipalDetails principalDetails) {
        System.out.println("comment 생성 시작");
        params.setTbUserId(principalDetails.getTbUser().getId());
        params.setNick(principalDetails.getTbUser().getNick());
        return ResponseEntity.status(HttpStatus.CREATED).body(tbCommentService.create(params));
    }

    @Operation(summary = "댓글 수정",
            description = "기존에 존재하는 댓글을 수정하기 위한 컨트롤러 (접근권한: ALL) <br / >"
                    + "@param TbCmtUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbCmtAfterUpdateDto\\> <<br />"
                    + "@exception 중복 <br />")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbCmtAfterUpdateDto> update(@RequestBody TbCmtUpdateDto params) {

        return ResponseEntity.status(HttpStatus.OK).body(tbCommentService.update(params));
    }

    @Operation(summary = "댓글 삭제",
            description = "기존에 존재하는 댓글을 삭제하기 위한 컨트롤러 (접근권한: ALL) <br / >"
                    + "@param TbCmtDeleteDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbCmtAfterDeleteDto\\> <<br />"
                    + "@exception 중복 <br />")
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<TbCmtAfterUpdateDto> delete(@RequestBody TbCmtDeleteDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbCommentService.delete(params));
    }

    @Operation(summary = "댓글 전체 목록 조회",
            description = "게시판 댓글 전체 목록 조회를 위한 컨트롤러 <br />"
                    + "@param (TbCmtListDto) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<List<TbCmtAfterSelectDto>\\> <br />"
                    + "@exception (no exception) <br />")
    @PreAuthorize("permitAll()")
    @PostMapping("/list")
    public ResponseEntity<List<TbCmtAfterSelectDto>> list(@RequestBody TbCmtListDto params) {
        List<TbCmtAfterSelectDto> tbCmtAfterSelectDtoList = tbCommentService.list(params);
        return ResponseEntity.status(HttpStatus.OK).body(tbCmtAfterSelectDtoList);
    }
}
