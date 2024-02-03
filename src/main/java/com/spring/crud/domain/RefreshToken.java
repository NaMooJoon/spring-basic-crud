package com.spring.crud.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.sql.Ref;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(indexes = {
        @Index(columnList = "tbUserId"),
})
@Entity
public class RefreshToken extends AuditingFields {

    @Column(nullable = false)
    private String tbUserId;

    @Column(nullable = false, length = 2_000)
    private String content;

    protected RefreshToken() {}
    private RefreshToken(String tbUserId, String content) {
        this.tbUserId = tbUserId;
        this.content = content;
    }

    public static RefreshToken of(String tbUserId, String content) {
        return new RefreshToken(tbUserId, content);
    }

}
