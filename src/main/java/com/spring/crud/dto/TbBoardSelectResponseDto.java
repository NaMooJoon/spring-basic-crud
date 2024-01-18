package com.spring.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TbBoardSelectResponseDto {

    @Schema(description = "id", example = "board id")
    private String id;

    @Schema(description = "title", example = "title")
    private String title;

    @Schema(description = "content", example = "content")
    private String content;

    @Schema(description = "deleted", example = "N")
    private String deleted;

    @Schema(description = "createdAt", example="2024-01-01 00:00:00.000000")
    private String createdAt;

    @Schema(description = "modifiedAt", example="2024-01-01 00:00:00.000000")
    private String modifiedAt;
}
