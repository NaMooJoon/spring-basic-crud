package com.spring.crud.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(name = "IDX_tbuserroltype_createdAt", columnList = "createdAt"),
        @Index(name = "IDX_tbuserroltype_modifiedAt", columnList = "modifiedAt")
})
@Entity
public class TbUserRoleType extends AuditingFields {

    @ManyToOne
    @JoinColumn(name = "tb_user_id")
    private TbUser tbUser;

    @ManyToOne
    @JoinColumn(name = "role_type_id")
    private RoleType roleType;

    protected TbUserRoleType() {}

    private TbUserRoleType(TbUser tbUser, RoleType roleType) {
        this.tbUser = tbUser;
        this.roleType = roleType;
    }

    public static TbUserRoleType of(TbUser tbUser, RoleType roleType) {
        return new TbUserRoleType(tbUser, roleType);
    }
}
