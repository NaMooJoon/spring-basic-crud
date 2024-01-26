package com.spring.crud.domain;

import com.spring.crud.dto.TbUserDto.TbUserAfterCreateDto;
import com.spring.crud.dto.TbUserDto.TbUserAfterUpdateDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Table(
        indexes = {
                @Index(name = "IDX_tb_user_createdAt", columnList = "createdAt"),
                @Index(name = "IDX_tb_user_modifiedAt", columnList = "modifiedAt"),
                @Index(name = "IDX_tb_user_process", columnList = "process")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "UQ_tb_user_uid", columnNames = {"uid"}),
                @UniqueConstraint(name = "UQ_tb_user_nick", columnNames = {"nick"})
        }
)
@Entity
public class TbUser extends AuditingFields {

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String nick;

    @Column(nullable = false)
    private String joinFrom;

    @Column(nullable = false)
    private String process;


    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true, length = 10_000)
    private String content;

    protected TbUser() {}

    @Builder
    private TbUser(String uid, String pw, String nick, String joinFrom, String process) {
        this.uid = uid;
        this.pw = pw;
        this.nick = nick;
        this.joinFrom = joinFrom;
        this.process = process;
    }

    public TbUserAfterCreateDto toAfterCreateDto() {
        return TbUserAfterCreateDto.builder()
                .id(super.getId())
                .build();
    }
    public TbUserAfterUpdateDto toAfterUpdateDto() {
        return TbUserAfterUpdateDto.builder()
                .id(super.getId())
                .build();
    }
}
