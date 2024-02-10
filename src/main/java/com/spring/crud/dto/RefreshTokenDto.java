package com.spring.crud.dto;

import com.spring.crud.domain.RefreshToken;
import com.spring.crud.dto.common.CommonPagedListRequestDto;
import com.spring.crud.dto.common.CommonScrollListRequestDto;
import com.spring.crud.dto.common.CommonSelectDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class RefreshTokenDto {
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RefreshTokenAfterCreateDto {

        @Schema(description = "id", example = "length32textNumber")
        private String id;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RefreshTokenAfterSelectDto extends CommonSelectDto {

        @Schema(description = "content", example = "contents ...")
        private String content;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RefreshTokenAfterUpdateDto {

        @Schema(description = "id", example = "length32textnumber")
        private String id;

        @Schema(description = "deleted", example = "Y")
        private String deleted;

        @Schema(description = "content", example = "contents ...")
        @Size(max = 300)
        private String content;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RefreshTokenCreateDto {

        @Schema(description = "username", example = "author username")
        private String username;

        @Schema(description = "content", example = "contents ...")
        @NotNull
        @NotEmpty
        @Size(max = 300)
        private String content;

        public RefreshToken toEntity() {
            return RefreshToken.of(username, content);
        }
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RefreshTokenDeleteDto {

        @Schema(description = "id", example = "length32textnumber")
        @NotNull
        @NotEmpty
        @Size(max = 32)
        private String id;
    }

}
