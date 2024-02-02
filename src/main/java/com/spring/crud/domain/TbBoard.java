package com.spring.crud.domain;

import com.spring.crud.dto.TbBoardDto.TbBoardCreateResponseDto;
import com.spring.crud.dto.TbBoardDto.TbBoardUpdateResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "modifiedAt")
})
@Entity
public class TbBoard extends AuditingFields {

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false, length = 10_000)
    private String content;

    @Setter
    @Column(nullable = false)
    private String tbUserId;

    protected TbBoard() {}

    public TbBoard(String title, String content, String tbUserId) {
        this.title = title;
        this.content = content;
        this.tbUserId = tbUserId;
    }

    public static TbBoard of(String title, String content, String tbUserId) {
        return new TbBoard(title, content, tbUserId);
    }

    public TbBoardCreateResponseDto toCreateResponseDto() {
        return TbBoardCreateResponseDto.builder()
                .id(super.getId())
                .title(title)
                .content(content)
                .build();
    }
    public TbBoardUpdateResponseDto toUpdateResponseDto() {
        return TbBoardUpdateResponseDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .title(title)
                .content(content)
                .build();
    }
}
