package com.spring.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TbUserAfterSelectDto {

    @Schema(description = "id", example="id")
    private String id;

    @Schema(description = "E-mail(id)", example="id")
    private String uid;

    @Schema(description = "비밀번호", example="abcd1234!")
    private String pw;

    @Schema(description = "가입경로", example="")
    private String joinFrom;

    @Schema(description = "닉네임", example="Horse")
    private String nick;

    @Schema(description = "이름", example="Kim")
    private String name;

    @Schema(description = "단계", example="0")
    private String process;

    @Schema(description = "전화번호", example="1234")
    private String phone;

    @Schema(description = "대표사진", example="~~~~.png")
    private String image;

    @Schema(description = "삭제 여부", example="Y")
    private String deleted;

    @Schema(description = "created_at", example="2024-01-01 00:00:00.000000")
    private String created_at;

    @Schema(description = "modified_at", example="2024-01-01 00:00:00.000000")
    private String modified_at;
}
