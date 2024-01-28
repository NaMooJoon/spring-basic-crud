package com.spring.crud.domain;

import com.spring.crud.dto.TbCmtDto.TbCmtAfterCreateDto;
import com.spring.crud.dto.TbCmtDto.TbCmtAfterUpdateDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(indexes = {
        @Index(columnList = "tbBoardId"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "modifiedAt")
})
@Entity
public class TbComment extends AuditingFields {

    @Column(nullable = false)
    private String tbBoardId; // Board pk
    @Column(nullable = false, length = 10_000)
    private String content;

    protected TbComment() {}
    private TbComment(String tbBoardId, String content) {
        this.tbBoardId = tbBoardId;
        this.content = content;
    }

    public static TbComment of(String tbBoardId, String content) {
        return new TbComment(tbBoardId, content);
    }

    public TbCmtAfterCreateDto toAfterCreateDto() {
        return TbCmtAfterCreateDto.builder()
                .content(content)
                .build();
    }

    public TbCmtAfterUpdateDto toAfterUpdateDto() {
        return TbCmtAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .tbBoardId(tbBoardId)
                .content(content)
                .build();
    }

}
