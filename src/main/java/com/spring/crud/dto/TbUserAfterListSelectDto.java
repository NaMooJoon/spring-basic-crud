package com.spring.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class TbUserAfterListSelectDto {

    @Schema(description = "id", example = "length32TextNumber")
    private String id;

    @Schema(description = "nick", example = "닉네임")
    @Size(max = 50)
    private String nick;

    @Schema(description = "joinFrom", example = "회원 가입 경로")
    @Size(max = 50)
    private String joinFrom;

    @Schema(description = "name", example = "이름")
    @Size(max = 50)
    private String name;

    @Schema(description = "phone", example = "전화번호")
    @Size(max = 50)
    private String phone;

    @Schema(description = "image", example = "image url")
    private String image;

    @Schema(description = "deleted", example = "N or Y")
    private String deleted;

    @Schema(description = "createdAt", example = "2024-01-01 00:00:00.000000")
    private String createdAt;
}
