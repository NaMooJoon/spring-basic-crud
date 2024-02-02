package com.spring.crud.security;

import com.spring.crud.domain.TbUser;
import com.spring.crud.exception.NoMatchedDataException;
import com.spring.crud.repository.TbUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final TbUserRepository tbUserRepository;

    public PrincipalDetailsService(TbUserRepository tbUserRepository) {
        this.tbUserRepository = tbUserRepository;
    }

    /**
     * principalDetails 생성을 위한 함수
     * username으로 tbUser 조회, principalDetails 생성
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbUser tbUser = tbUserRepository.findByUsername(username);
        if (tbUser == null) {
            throw new NoMatchedDataException("username : " + username);
        }
        return new PrincipalDetails(tbUser);
    }

}
