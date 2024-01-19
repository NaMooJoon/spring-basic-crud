package com.spring.crud.dto;

import com.spring.crud.domain.TbUser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TbUserCreateDto {

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
