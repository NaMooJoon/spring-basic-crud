package com.spring.crud.dto;

import com.spring.crud.domain.TbBoard;
import com.spring.crud.dto.common.CommonPagedListRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TbBoardDto {
    @Getter
    public static class TbBoardCreateRequestDto {

        @Schema(description = "title", example = "Post Title...")
        @NotNull
        @NotEmpty
        @Size(max = 100)
        private String title;

        @Schema(description = "content", example = "Content is ...")
        @Size(max = 10_000)
        private String content;

        public TbBoard toEntity() {
            return TbBoard.of(title, content);
        }
    }

    @Getter
    public static class TbBoardCreateResponseDto {

        @Schema(description = "title", example = "Post Title...")
        @NotNull
        @NotEmpty
        @Size(max = 100)
        private String title;

        @Schema(description = "content", example = "Content is ...")
        @Size(max = 10_000)
        private String content;

        @Builder
        public TbBoardCreateResponseDto(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbBoardSelectResponseDto {

        @Schema(description = "id", example = "board id")
        private String id;

        @Schema(description = "title", example = "title")
        private String title;

        @Schema(description = "content", example = "content")
        private String content;

        @Schema(description = "deleted", example = "N")
        private String deleted;

        @Schema(description = "createdAt", example="2024-01-01 00:00:00.000000")
        private String createdAt;

        @Schema(description = "modifiedAt", example="2024-01-01 00:00:00.000000")
        private String modifiedAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TbBoardListRequestDto {

        @Schema(description = "title", example = "title")
        private String title;

        @Schema(description = "delete", example = "N")
        private String deleted;
    }

    @Getter
    public static class TbBoardUpdateRequestDto {

        @Schema(description = "id", example = "length32textnumber")
        @NotNull
        @NotEmpty
        @Size(max = 32)
        private String id;

        @Schema(description = "title", example = "Post Title...")
        @Size(max = 100)
        private String title;

        @Schema(description = "deleted", example = "Y")
        @Size(max = 1)
        private String deleted;

        @Schema(description = "content", example = "Content is ...")
        @Size(max = 10_000)
        private String content;
    }

    @Builder
    @Getter
    public static class TbBoardUpdateResponseDto {

        @Schema(description = "id", example = "length32textnumber")
        private String id;

        @Schema(description = "title", example = "Post Title...")
        private String title;

        @Schema(description = "deleted", example = "Y")
        private String deleted;

        @Schema(description = "content", example = "Content is ...")
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbBoardPagedRequestDto extends CommonPagedListRequestDto {

        @Schema(description = "title", example = "title")
        private String title;

        @Schema(description = "delete", example = "N")
        private String deleted;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbBoardPageListRequest extends CommonPagedListRequestDto {

        @Schema(description = "title", example = "title")
        private String title;

        @Schema(description = "deleted", example = "Y")
        private String deleted;
    }
}
