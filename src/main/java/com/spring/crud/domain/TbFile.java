package com.spring.crud.domain;

import com.spring.crud.dto.TbFileDto.TbFileAfterCreateDto;
import com.spring.crud.dto.TbFileDto.TbFileAfterUpdateDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "tbBoardId"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "modifiedAt")
})
@Entity
public class TbFile extends AuditingFields {

    @Column(nullable = false)
    private String tbBoardId; // Board pk
    @Column(nullable = false, length = 10_000)
    private String content;

    protected TbFile() {
    }

    private TbFile(String tbBoardId, String content) {
        this.tbBoardId = tbBoardId;
        this.content = content;
    }

    public static TbFile of(String tbBoardId, String content) {
        return new TbFile(tbBoardId, content);
    }

    public TbFileAfterCreateDto toAfterCreateDto() {
        return TbFileAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }

    public TbFileAfterUpdateDto toAfterUpdateDto() {
        return TbFileAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .tbBoardId(getTbBoardId())
                .content(getContent())
                .build();
    }

}
