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
        @Index(columnList = "username"),
})
@Entity
public class RefreshToken extends AuditingFields {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 2_000)
    private String content;

    protected RefreshToken() {}
    private RefreshToken(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public static RefreshToken of(String tbUserId, String content) {
        return new RefreshToken(tbUserId, content);
    }

}
