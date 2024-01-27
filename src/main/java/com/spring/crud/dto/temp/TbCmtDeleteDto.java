package com.spring.crud.dto.temp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class TbCmtDeleteDto {

    @Schema(description = "id", example = "length32textnumber")
    @NotNull
    @NotEmpty
    @Size(max = 32)
    private String id;
}
