package com.spring.crud.dto.temp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
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
public class TbCmtAfterCreateDto {

    @Schema(description = "content", example = "contents ...")
    @Size(max = 300)
    private String content;
}
