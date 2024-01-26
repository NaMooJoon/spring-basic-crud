package com.spring.crud.dto.temp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TbCmtUpdateDto {

    @Schema(description = "id", example = "length32textnumber")
    @NotNull
    @NotEmpty
    @Size(max = 32)
    private String id;

    @Schema(description = "tbBoardId", example = "Board id")
    @Size(max = 100)
    private String tbBoardId;

    @Schema(description = "deleted", example = "Y")
    private String deleted;

    @Schema(description = "content", example = "contents ...")
    @Size(max = 300)
    private String content;
}
