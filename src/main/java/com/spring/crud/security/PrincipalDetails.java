package com.spring.crud.security;

import com.spring.crud.domain.TbUser;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
public class PrincipalDetails implements UserDetails {

    private final TbUser tbUser;

    public PrincipalDetails(TbUser tbUser) {
        this.tbUser = tbUser;
    }

    /**
     * TbUser Role 파싱하는 함수
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        tbUser.getRoleList().forEach(tbUserRoleType -> {
            authorities.add(() -> tbUserRoleType.getRoleType().getTypeName());
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return tbUser.getPassword();
    }

    @Override
    public String getUsername() {
        return tbUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
