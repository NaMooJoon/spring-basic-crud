package com.spring.crud.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonScrollListRequestDto {

    @Schema(description = "검색 기준일(시작)", example="1970-01-01")
    private String beginDate;
    @Schema(description = "검색 기준일(종료)", example="1970-01-01")
    private String endDate;

    @NotNull
    @NotEmpty
    @Schema(description = "더보기 요청시 불러올 글 갯수", example = "10")
    private int requestBoardSize;

    @Schema(description = "검색 기준일시", example = "1970-01-01 00:00:00.000000")
    private String searchDate;

    @Schema(description = "조회 방향", example = "forwards, backwards")
    private String searchWay;

    public void afterBuild() {
        if (requestBoardSize <= 0) {
            requestBoardSize = 5;
        }
        if (searchDate == null || "".equals(searchDate)) {
            searchDate = "9999-12-31 23:59:59.999999";
            searchWay = "backward";
        }
    }
}
