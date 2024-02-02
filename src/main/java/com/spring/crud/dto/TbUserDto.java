package com.spring.crud.dto;

import com.spring.crud.domain.TbUser;
import com.spring.crud.dto.common.CommonSelectDto;
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
    public static class TbUserLoginDto {

        @Schema(description = "username", example = "xxx@xxxx.com")
        @NotNull
        @NotEmpty
        @Size(max = 191)
        private String username;

        @Schema(description = "min 8, 1 upper case, 1 number", example = "testPassword1!")
        @NotNull
        @NotEmpty
        @Size(min = 8, max = 191)
        private String password;

        public TbUser toEntity() {
            return TbUser.builder()
                    .username(username)
                    .password(password)
                    .process("0")
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbUserCreateDto {

        @Schema(description = "username", example = "이메일 주소")
        @NotNull
        @Email
        @NotEmpty
        @Size(max = 100)
        private String username;

        @Schema(description = "password", example = "비밀번호")
        @NotNull
        @NotEmpty
        @Size(max = 50)
        private String password;

        @Schema(description = "nick", example = "닉네임")
        @Size(max = 50)
        private String nick;


        @Schema(description = "joinFrom", example = "가입 경로")
        @Size(max = 50)
        private String joinFrom;

        @Schema(description = "process", example = "진행 단계")
        @Size(max = 50)
        private String process;

        public TbUser toEntity() {
            return TbUser.builder()
                    .username(username)
                    .password(password)
                    .nick(nick)
                    .joinFrom(joinFrom)
                    .process(process)
                    .build();
        }
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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbUserUpdateDto {

        @Schema(description = "id", example = "User table id")
        @NotNull
        @NotEmpty
        @Size(max = 12)
        private String id;

        @Schema(description = "deleted", example = "Y or N")
        @Size(max = 1)
        private String deleted;

        @Schema(description = "password", example = "비밀번호")
        private String password;

        @Schema(description = "nick", example = "닉네임")
        @Size(max = 50)
        private String nick;

        @Schema(description = "joinFrom", example = "DIRECT, NAVER, GOOGLE")
        @Size(max = 20)
        private String joinFrom;

        @Schema(description = "process", example = "0")
        @Size(max = 20)
        private String process;

        @Schema(description = "name", example = "이름")
        @Size(max = 50)
        private String name;

        @Schema(description = "phone", example = "전화번호")
        @Size(max = 50)
        private String phone;

        @Schema(description = "image", example = "이미지 URL")
        private String image;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbUserAfterUpdateDto {

        @Schema(description = "id", example = "length32TextNumber")
        private String id;

        @Schema(description = "deleted", example = "Y or N")
        private String deleted;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbUserAfterSelectDto extends CommonSelectDto {

        @Schema(description = "E-mail(id)", example="id")
        private String username;

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
    }
    @Getter
    @Service
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbUserListSelectDto extends CommonSelectDto {

        @Schema(description = "username", example = "이메일 주소")
        @Email
        @Size(max = 100)
        private String username;

        @Schema(description = "joinFrom", example = "회원 가입 경로")
        @Size(max = 50)
        private String joinFrom;

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
    @Service
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbUserAfterListSelectDto extends CommonSelectDto {

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

    }
}
