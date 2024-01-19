package com.spring.crud.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonPagedListRequestDto {

    @Schema(description = "검색 기준일 (시작)", example = "1970-01-01")
    private String beginDate;

    @Schema(description = "검색 기준일 (종료)", example = "1970-01-01")
    private String endDate;

    @NotNull
    @NotEmpty
    @Schema(description = "요청 페이지", example = "1")
    private int callPage;

    @Schema(description = "요청 페이지에 해당되는 글 목록 번호", example = "10")
    private int offset;

    @NotNull
    @NotEmpty
    @Schema(description = "한 페이지에 보여질 글의 갯수", example = "10")
    private int perPage;

    @Schema(description = "정렬 기준", example = "createAt")
    private String orderBy;

    @Schema(description = "정렬 방향", example = "DESC")
    private String orderWay;

    public int[] afterBuild(int listSize) {

        adjustPerPage();

        int totalPageSize = calculateTotalPageSize(listSize);
        adjustCallPage(totalPageSize);

        offset = (callPage - 1) * perPage;
        int[] result = {callPage, perPage, totalPageSize, listSize};
        return result;
    }

    private void adjustPerPage() {
        if (perPage <= 0) {
            perPage = 10;
        }
    }

    private int calculateTotalPageSize(int listSize) {
        int remainder = listSize % perPage;
        int totalPageSize = listSize / perPage;

        if(remainder > 0){
            totalPageSize++;
        }
        return Math.max(1, totalPageSize);
    }

    private void adjustCallPage(int totalPageSize) {
        if (callPage < 1) {
            callPage = 1;
        }
        if (callPage > totalPageSize) {
            callPage = totalPageSize;
        }
    }

}
