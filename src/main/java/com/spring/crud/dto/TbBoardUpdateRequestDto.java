package com.spring.crud.dto;

import com.spring.crud.domain.TbBoard;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TbBoardUpdateRequestDto {

    @Schema(description = "id", example = "length32textnumber")
    @NotNull
    @NotEmpty
    @Size(max = 32)
    private String id;

    @Schema(description = "title", example = "Post Title...")
    @Size(max = 100)
    private String title;

    @Schema(description = "deleted", example = "Y")
    @Size(max = 1)
    private String deleted;

    @Schema(description = "content", example = "Content is ...")
    @Size(max = 10_000)
    private String content;
}
