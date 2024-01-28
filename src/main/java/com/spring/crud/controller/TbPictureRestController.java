package com.spring.crud.controller;

import com.spring.crud.dto.TbPictureDto.TbPictureAfterCreateDto;
import com.spring.crud.dto.TbPictureDto.TbPictureAfterSelectDto;
import com.spring.crud.dto.TbPictureDto.TbPictureAfterUpdateDto;
import com.spring.crud.dto.TbPictureDto.TbPictureCreateDto;
import com.spring.crud.dto.TbPictureDto.TbPictureDeleteDto;
import com.spring.crud.dto.TbPictureDto.TbPictureListDto;
import com.spring.crud.dto.TbPictureDto.TbPictureScrollListDto;
import com.spring.crud.dto.TbPictureDto.TbPictureUpdateDto;
import com.spring.crud.service.TbPictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "1. 게시글 이미지 API 안내",
        description = "게시글 이미지 관련 기능 정의한 RestController.")
@RequestMapping("/api/picture")
@Controller
public class TbPictureRestController {

    TbPictureService tbPictureService;

    @Autowired
    public TbPictureRestController(TbPictureService TbPictureService) {
        this.tbPictureService = TbPictureService;
    }

    @Operation(summary = "이미지 등록",
            description = "게시판 안에 신규 이미지 등록을 위한 컨트롤러 (접근권한: ALL) <br / >"
                    + "@param TbPictureCreateDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbPictureAfterCreateDto\\> <<br />"
                    + "@exception 중복 <br />")
    @PostMapping("")
    public ResponseEntity<TbPictureAfterCreateDto> create(@RequestBody TbPictureCreateDto params) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbPictureService.create(params));
    }

    @Operation(summary = "이미지 수정",
            description = "기존에 존재하는 이미지을 수정하기 위한 컨트롤러 (접근권한: ALL) <br / >"
                    + "@param TbPictureUpdateDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbPictureAfterUpdateDto\\> <<br />"
                    + "@exception 중복 <br />")
    @PutMapping("")
    public ResponseEntity<TbPictureAfterUpdateDto> update(@RequestBody TbPictureUpdateDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbPictureService.update(params));
    }

    @Operation(summary = "이미지 삭제",
            description = "기존에 존재하는 이미지을 삭제하기 위한 컨트롤러 (접근권한: ALL) <br / >"
                    + "@param TbPictureDeleteDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbPictureAfterUpdateDto\\> <<br />"
                    + "@exception 중복 <br />")
    @DeleteMapping("")
    public ResponseEntity<TbPictureAfterUpdateDto> delete(@RequestBody TbPictureDeleteDto params) {
        return ResponseEntity.status(HttpStatus.OK).body(tbPictureService.delete(params));
    }

    @Operation(summary = "이미지 전체 목록 조회",
            description = "게시판 이미지 전체 목록 조회를 위한 컨트롤러 <br />"
                    + "@param (TbPictureListDto) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<List<TbPictureAfterSelectDto>\\> <br />"
                    + "@exception (no exception) <br />")
    @PostMapping("/list")
    public ResponseEntity<List<TbPictureAfterSelectDto>> list(@RequestBody TbPictureListDto params) {
        List<TbPictureAfterSelectDto> tbPictureAfterSelectDtoList = tbPictureService.list(params);
        return ResponseEntity.status(HttpStatus.OK).body(tbPictureAfterSelectDtoList);
    }

    @Operation(summary = "이미지 전체 목록 조회 (more)",
            description = "게시판 이미지 전체 목록 조회를 위한 컨트롤러 <br />"
                    + "@param (TbPictureListDto) <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<List<TbPictureAfterSelectDto>\\> <br />"
                    + "@exception (no exception) <br />")
    @PostMapping("/scroll")
    public ResponseEntity<List<TbPictureAfterSelectDto>> scroll(@RequestBody TbPictureScrollListDto params) {
        List<TbPictureAfterSelectDto> tbPictureAfterSelectDtoList = tbPictureService.moreList(params);
        return ResponseEntity.status(HttpStatus.OK).body(tbPictureAfterSelectDtoList);
    }
}
