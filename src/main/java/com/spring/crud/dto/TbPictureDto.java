package com.spring.crud.dto;

import com.spring.crud.domain.TbComment;
import com.spring.crud.domain.TbPicture;
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

public class TbPictureDto {
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbPictureAfterCreateDto {

        @Schema(description = "id", example = "length32textnumber")
        private String id;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbPictureAfterSelectDto extends CommonSelectDto {

        @Schema(description = "tbBoardId", example = "Board id")
        private String tbBoardId;

        @Schema(description = "content", example = "contents ...")
        private String content;

    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbPictureAfterUpdateDto {

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

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbPictureCreateDto {

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

        public TbPicture toEntity() {
            return TbPicture.of(tbBoardId, content);
        }
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbPictureDeleteDto {

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
    public static class TbPictureListDto {
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
    public static class TbPicturePagedListDto extends CommonPagedListRequestDto {
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
    public static class TbPictureScrollListDto extends CommonScrollListRequestDto {
        @Schema(description = "tbBoardId", example = "Board Id")
        private String tbBoardId;
        @Schema(description = "content", example="content")
        private String content;
        @Schema(description = "deleted", example="N")
        private String deleted;
    }

    @Getter
    @Setter
    @Builder
    public static class TbPictureUpdateDto {

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
}
