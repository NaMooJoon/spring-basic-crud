package com.spring.crud.config;

import com.spring.crud.domain.TbUser;
import com.spring.crud.repository.TbUserRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final TbUserRepository tbUserRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        validateAttributesOfOauth2User(oAuth2User.getAttributes());
        saveOrUpdate(oAuth2User.getAttributes());

        return oAuth2User;
    }

    private void validateAttributesOfOauth2User(Map<String, Object> attributes) {
        if (!(attributes.containsKey("email") && attributes.containsKey("sub"))) {
            throw new OAuth2AuthenticationException("There is no proper attribute in oauth response.");
        }
    }

    private void saveOrUpdate(Map<String, Object> attributes) {
        TbUser tbUser = tbUserRepository.findByUsername((String) attributes.get("email"))
                .orElse(TbUser.builder()
                        .username((String) attributes.get("email"))
                        .password((String) attributes.get("sub"))
                        .name((String) attributes.get("name"))
                        .image((String) attributes.get("picture"))
                        .build());
        tbUserRepository.save(tbUser);
    }
}
