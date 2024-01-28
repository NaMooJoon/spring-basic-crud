package com.spring.crud.domain;

import com.spring.crud.dto.TbPictureDto.TbPictureAfterCreateDto;
import com.spring.crud.dto.TbPictureDto.TbPictureAfterUpdateDto;
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
public class TbPicture extends AuditingFields {

    @Column(nullable = false)
    private String tbBoardId; // Board pk
    @Column(nullable = false, length = 10_000)
    private String content;

    protected TbPicture() {
    }

    private TbPicture(String tbBoardId, String content) {
        this.tbBoardId = tbBoardId;
        this.content = content;
    }

    public static TbPicture of(String tbBoardId, String content) {
        return new TbPicture(tbBoardId, content);
    }

    public TbPictureAfterCreateDto toAfterCreateDto() {
        return TbPictureAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }

    public TbPictureAfterUpdateDto toAfterUpdateDto() {
        return TbPictureAfterUpdateDto.builder()
                .id(super.getId())
                .deleted(super.getDeleted())
                .tbBoardId(getTbBoardId())
                .content(getContent())
                .build();
    }

}
