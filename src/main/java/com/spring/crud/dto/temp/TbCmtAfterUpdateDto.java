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
public class TbCmtAfterUpdateDto {

    @Schema(description = "id", example = "length32textnumber")
    private String id;

    @Schema(description = "tbBoardId", example = "Board id")
    private String tbBoardId;

    @Schema(description = "deleted", example = "Y")
    private String deleted;

    @Schema(description = "content", example = "contents ...")
    @Size(max = 300)
    private String content;
}
