package com.spring.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TbBoardSelectResponseDto {

    @Schema(description = "id", example = "board id")
    private String id;

    @Schema(description = "title", example = "title")
    private String title;

    @Schema(description = "content", example = "content")
    private String content;

    @Schema(description = "deleted", example = "N")
    private String deleted;
}
