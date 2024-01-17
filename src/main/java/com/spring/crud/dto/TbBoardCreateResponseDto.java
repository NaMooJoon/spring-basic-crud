package com.spring.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TbBoardCreateResponseDto {

    @Schema(description = "title", example = "Post Title...")
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String title;

    @Schema(description = "content", example = "Content is ...")
    @Size(max = 10_000)
    private String content;

    @Builder
    public TbBoardCreateResponseDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
