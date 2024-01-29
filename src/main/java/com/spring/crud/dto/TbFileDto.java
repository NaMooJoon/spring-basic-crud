package com.spring.crud.dto;

import com.spring.crud.domain.TbFile;
import com.spring.crud.dto.common.CommonPagedListRequestDto;
import com.spring.crud.dto.common.CommonScrollListRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TbFileDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbFileCreateDto {

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

        public TbFile toEntity() {
            return TbFile.of(tbBoardId, content);
        }
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbFileAfterCreateDto {

        @Schema(description = "id", example = "length32textnumber")
        private String id;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbFileAfterSelectDto {

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


    @Getter
    @Setter
    @Builder
    public static class TbFileUpdateDto {

        @Schema(description = "id", example = "length32textnumber")
        @NotNull
        @NotEmpty
        @Size(max = 32)
        private String id;

        @Schema(description = "tbBoardId", example = "Board id")
        @Size(max = 100)
        private String tbBoardId;

        @Schema(description = "deleted", example = "Y")
        @Size(max = 1)
        private String deleted;

        @Schema(description = "content", example = "contents ...")
        @Size(max = 300)
        private String content;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbFileAfterUpdateDto {

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

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbFileDeleteDto {

        @Schema(description = "id", example = "length32textnumber")
        @NotNull
        @NotEmpty
        @Size(max = 32)
        private String id;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbFileListDto {
        @Schema(description = "tbBoardId", example = "Board Id")
        private String tbBoardId;
        @Schema(description = "content", example="content")
        private String content;
        @Schema(description = "deleted", example="N")
        private String deleted;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbFilePagedListDto extends CommonPagedListRequestDto {
        @Schema(description = "tbBoardId", example = "Board Id")
        private String tbBoardId;
        @Schema(description = "content", example="content")
        private String content;
        @Schema(description = "deleted", example="N")
        private String deleted;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbFileScrollListDto extends CommonScrollListRequestDto {
        @Schema(description = "tbBoardId", example = "Board Id")
        private String tbBoardId;
        @Schema(description = "content", example="content")
        private String content;
        @Schema(description = "deleted", example="N")
        private String deleted;
    }
}
