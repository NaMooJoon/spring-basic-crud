package com.spring.crud.dto;

import com.spring.crud.dto.common.CommonScrollListRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TbBoardScrollListRequestDto extends CommonScrollListRequestDto {

    @Schema(description = "title", example = "title is ...")
    private String title;

    @Schema(description = "deleted", example = "N")
    private String deleted;
}
