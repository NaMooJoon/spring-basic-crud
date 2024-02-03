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
    @Column(nullable = false)
    private String tbUserId; // User pk
    @Column(nullable = false)
    private String nick; // User nick name

    protected TbComment() {}
    private TbComment(String tbBoardId, String content, String tbUserId, String nick) {
        this.tbBoardId = tbBoardId;
        this.content = content;
        this.tbUserId = tbUserId;
        this.nick = nick;
    }

    public static TbComment of(String tbBoardId, String content, String tbUserId, String nick) {
        return new TbComment(tbBoardId, content, tbUserId, nick);
    }

    public TbCmtAfterCreateDto toAfterCreateDto() {
        return TbCmtAfterCreateDto.builder()
                .id(super.getId())
                .tbUserId(tbUserId)
                .nick(nick)
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
