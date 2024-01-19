package com.spring.crud.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonPagedListResponseDto<T> {

    @Schema(description = "요청 페이지", example = "1")
    private int callPage;

    @Schema(description = "마지막 페이지 수", example = "100")
    private int lastPage;

    @Schema(description = "한 페이지에 조회할 수 있는 글 목록 갯수", example = "10")
    private int perPage;

    @Schema(description = "전체 글 목록 갯수", example = "220")
    private int listSize;

    @Schema(description = "리스트 데이터", example = "글 목록 리스트")
    private List<T> list;

    public CommonPagedListResponseDto(int[] arguments, List<T> list) {
        this.callPage = arguments[0];
        this.perPage = arguments[1];
        this.lastPage = arguments[2];
        this.listSize = arguments[3];
        this.list = list;
    }
}
