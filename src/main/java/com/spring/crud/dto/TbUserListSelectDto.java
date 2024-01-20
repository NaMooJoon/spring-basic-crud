package com.spring.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class TbUserListSelectDto {

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
