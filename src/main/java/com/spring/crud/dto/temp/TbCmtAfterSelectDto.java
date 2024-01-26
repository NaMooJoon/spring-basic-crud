package com.spring.crud.dto.temp;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class TbCmtAfterSelectDto {

    @Schema(description = "id", example = "id")
    private String id;

    @Schema(description = "tbBoardId", example = "Board id")
    private String tbBoardId;

    @Schema(description = "content", example = "contents ...")
    private String content;

    @Schema(description = "deleted", example = "Y or N")
    private String deleted;

    @Schema(description = "createdAt", example = "2024-01-01 00:00:00.000000")
    private String createdAt;

    @Schema(description = "modifiedAt", example = "2024-01-01 00:00:00.000000")
    private String modifiedAt;
}
