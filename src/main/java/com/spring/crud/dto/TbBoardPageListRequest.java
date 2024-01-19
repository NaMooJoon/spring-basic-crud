package com.spring.crud.dto;

import com.spring.crud.dto.common.CommonPagedListRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TbBoardPageListRequest extends CommonPagedListRequestDto {

    @Schema(description = "title", example = "title")
    private String title;

    @Schema(description = "deleted", example = "Y")
    private String deleted;
}
