package com.spring.crud.domain;

import com.spring.crud.dto.TbBoardCreateResponceDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title")
})
@Entity
public class TbBoard extends AuditingFields {

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false, length = 10_000)
    private String content;

    protected TbBoard() {}

    private TbBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static TbBoard of(String title, String content) {
        return new TbBoard(title, content);
    }

    public TbBoardCreateResponceDto toResponceDto() {
        return TbBoardCreateResponceDto.builder()
                .title(title)
                .content(content)
                .build();
    }
}