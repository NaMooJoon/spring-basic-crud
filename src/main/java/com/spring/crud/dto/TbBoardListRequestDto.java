package com.spring.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TbBoardListRequestDto {

    @Schema(description = "title", example = "title")
    private String title;

    @Schema(description = "delete", example = "N")
    private String deleted;
}
