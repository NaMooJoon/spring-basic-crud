package com.spring.crud.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonSelectDto {
    @Schema(description = "id", example = "데이블 pk")
    private String id;
    @Schema(description = "deleted", example = "Y or N")
    private String deleted;
    @Schema(description = "createdAt", example = "2024-01-01 00:00:00.000000")
    private String createdAt;
    @Schema(description = "modifiedAt", example = "2024-01-01 00:00:00.000000")
    private String modifiedAt;
}
