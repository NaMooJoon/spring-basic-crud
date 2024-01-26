package com.spring.crud.dto;

import com.spring.crud.domain.TbUser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

public class TbUserDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbUserCreateDto {

        @Schema(description = "uid", example = "이메일 주소")
        @NotNull
        @Email
        @NotEmpty
        @Size(max = 100)
        private String uid;

        @Schema(description = "pw", example = "비밀번호")
        @NotNull
        @NotEmpty
        @Size(max = 50)
        private String pw;

        @Schema(description = "nick", example = "닉네임")
        @NotNull
        @NotEmpty
        @Size(max = 50)
        private String nick;


        @Schema(description = "joinFrom", example = "가입 경로")
        @NotNull
        @NotEmpty
        @Size(max = 50)
        private String joinFrom;

        public TbUser toEntity() {
            return TbUser.builder()
                    .uid(uid)
                    .pw(pw)
                    .nick(nick)
                    .joinFrom(joinFrom)
                    .process("0")
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbUserUpdateDto {

        @Schema(description = "id", example = "table id")
        private String id;

        @Schema(description = "image", example = "이미지 URL")
        private String image;

        @Schema(description = "uid", example = "이메일 주소")
        @NotNull
        @Email
        @NotEmpty
        @Size(max = 100)
        private String uid;

        @Schema(description = "nick", example = "닉네임")
        @NotNull
        @NotEmpty
        @Size(max = 50)
        private String nick;

        @Schema(description = "name", example = "이름")
        @Size(max = 50)
        private String name;

        @Schema(description = "phone", example = "전화번호")
        @Size(max = 50)
        private String phone;
    }

    @Getter
    @Service
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbUserListSelectDto {

        @Schema(description = "uid", example = "이메일 주소")
        @Email
        @Size(max = 100)
        private String uid;

        @Schema(description = "joinFrom", example = "회원 가입 경로")
        @Size(max = 50)
        private String joinFrom;

        @Schema(description = "deleted", example = "N or Y")
        private String deleted;

        @Schema(description = "nick", example = "닉네임")
        @Size(max = 50)
        private String nick;

        @Schema(description = "name", example = "이름")
        @Size(max = 50)
        private String name;

        @Schema(description = "phone", example = "전화번호")
        @Size(max = 50)
        private String phone;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbUserAfterCreateDto {

        @Schema(description = "id", example = "length32TextNumber")
        private String id;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbUserAfterSelectDto {

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

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbUserAfterUpdateDto {

        @Schema(description = "id", example = "length32TextNumber")
        private String id;
    }

    @Getter
    @Service
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbUserAfterListSelectDto {

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
}
