package com.spring.crud.controller;

import com.spring.crud.util.FileUpload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "0. 기본 API 안내",
        description = "기본 기능 정의한 RestController.")
@RequestMapping("/api/default")
@RestController
public class DefaultRestController {
    @Operation(summary = "파일 업로드",
            description = "파일을 서버에 업로드 (일반) \n"
                    + "@param MultipartFile multipartFile \n"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<String\\> \n"
                    + "@exception \n"
    )
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@Valid @RequestParam MultipartFile file, HttpServletRequest request) {

        if (file == null || "".equals(file.getOriginalFilename() + "")) {
            return null;
        }

        String returnValue = null;
        try {
            returnValue = FileUpload.local(file, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
}
