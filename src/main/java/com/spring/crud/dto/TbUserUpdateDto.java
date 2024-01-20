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
public class TbUserUpdateDto {

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
