package com.spring.crud.dto.temp;

import com.spring.crud.domain.TbComment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbCmtCreateDto {

    @Schema(description = "tbBoardId", example = "Board id")
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String tbBoardId;

    @Schema(description = "content", example = "contents ...")
    @NotNull
    @NotEmpty
    @Size(max = 300)
    private String content;

    public TbComment toEntity() {
        return TbComment.of(tbBoardId, content);
    }
}
