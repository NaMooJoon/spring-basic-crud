package com.spring.crud.config;

import com.spring.crud.security.JwtTokenDto;
import com.spring.crud.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                   Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String username = (String) oAuth2User.getAttribute("email");

        String refreshToken = authService.createRefreshToken(username);
        JwtTokenDto jwtTokenDto = authService.issueAccessToken(refreshToken);

        String url = makeRedirectUrl(jwtTokenDto);
        if (response.isCommitted()) {
            System.err.println("응답이 이미 커밋된 상태");
        }
        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl(JwtTokenDto jwtTokenDto) {
        return UriComponentsBuilder.fromUriString("http://localhost:8080/oauth2/redirect")
                .queryParam("refreshToken", jwtTokenDto.getRefreshToken())
                .queryParam("accessToken", jwtTokenDto.getAccessToken())
                .build().toUriString();
    }
}
