package com.spring.crud.domain;

import com.spring.crud.dto.TbUserDto.TbUserAfterCreateDto;
import com.spring.crud.dto.TbUserDto.TbUserAfterUpdateDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

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
                @UniqueConstraint(name = "UQ_tb_user_nick", columnNames = {"nick"})
        }
)
@Entity
public class TbUser extends AuditingFields {

    @Column(nullable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String nick;

    @Column(nullable = true)
    private String joinFrom;

    @Column(nullable = true)
    private String process;


    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true, length = 10_000)
    private String content;

    @OneToMany(mappedBy = "tbUser", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<TbUserRoleType> tbUserRoleType = new ArrayList<>();

    public List<TbUserRoleType> getRoleList() {
        if (this.tbUserRoleType.size() > 0) {
            return tbUserRoleType;
        }
        return new ArrayList<>();
    }

    protected TbUser() {}
    @Builder
    private TbUser(String username, String password, String nick, String joinFrom, String process) {
        this.username = username;
        this.password = password;
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
