package com.spring.crud.dto.temp;

import com.spring.crud.dto.common.CommonScrollListRequestDto;
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
public class TbCmtScrollListDto extends CommonScrollListRequestDto {
    @Schema(description = "tbBoardId", example = "Board Id")
    private String tbBoardId;
    @Schema(description = "content", example="content")
    private String content;
    @Schema(description = "deleted", example="N")
    private String deleted;
}
