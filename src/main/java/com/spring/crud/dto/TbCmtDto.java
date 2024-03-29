package com.spring.crud.dto;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.spring.crud.domain.TbComment;
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

public class TbCmtDto {
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbCmtAfterCreateDto {

        @Schema(description = "id", example = "length32textNumber")
        private String id;

        @Schema(description = "authorId", example = "length32textNumber")
        private String tbUserId;

        @Schema(description = "nick", example = "author nick name")
        private String nick;

        @Schema(description = "content", example = "contents ...")
        @Size(max = 300)
        private String content;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbCmtAfterSelectDto extends CommonSelectDto {

        @Schema(description = "tbBoardId", example = "Board id")
        private String tbBoardId;

        @Schema(description = "nick", example = "author nick name")
        private String nick;

        @Schema(description = "content", example = "contents ...")
        private String content;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbCmtAfterUpdateDto {

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
    public static class TbCmtCreateDto {

        @Schema(description = "tbBoardId", example = "Board id")
        @NotNull
        @NotEmpty
        @Size(max = 100)
        private String tbBoardId;

        @Schema(description = "tbUserId", example = "author user id")
        private String tbUserId;

        @Schema(description = "nick", example = "author user nick name")
        private String nick;

        @Schema(description = "content", example = "contents ...")
        @NotNull
        @NotEmpty
        @Size(max = 300)
        private String content;

        public TbComment toEntity() {
            return TbComment.of(tbBoardId, content, tbUserId, nick);
        }
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbCmtDeleteDto {

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
    public static class TbCmtListDto {
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
    public static class TbCmtPagedListDto extends CommonPagedListRequestDto {
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
    public static class TbCmtScrollListDto extends CommonScrollListRequestDto {
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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TbCmtUpdateDto {

        @Schema(description = "id", example = "length32textnumber")
        @NotNull
        @NotEmpty
        @Size(max = 32)
        private String id;

        @Schema(description = "tbBoardId", example = "Board id")
        @Size(max = 100)
        private String tbBoardId;

        @Schema(description = "deleted", example = "Y")
        private String deleted;

        @Schema(description = "content", example = "contents ...")
        @Size(max = 300)
        private String content;
    }
}
