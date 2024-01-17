package com.spring.crud.dto;

import com.spring.crud.domain.TbBoard;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TbBoardUpdateResponseDto {

    @Schema(description = "id", example = "length32textnumber")
    private String id;

    @Schema(description = "title", example = "Post Title...")
    private String title;

    @Schema(description = "deleted", example = "Y")
    private String deleted;

    @Schema(description = "content", example = "Content is ...")
    private String content;
}