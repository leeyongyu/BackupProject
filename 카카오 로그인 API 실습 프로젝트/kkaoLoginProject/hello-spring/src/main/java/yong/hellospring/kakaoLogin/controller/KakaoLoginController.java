package yong.hellospring.kakaoLogin.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yong.hellospring.kakaoLogin.client.KakaoTokenFeignClient;
import yong.hellospring.kakaoLogin.client.KakaoUserInfoFeignClient;
import yong.hellospring.kakaoLogin.dto.KakaoTokenDto;
import yong.hellospring.kakaoLogin.dto.KakaoUserInfo;
import yong.hellospring.service.LoginValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoTokenFeignClient kakaoTokenFeignClient;
    private final KakaoUserInfoFeignClient kakaoFeignClient;

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @Value("${kakao.client.id}")
    private String clientId;

    private final String GRANT_TYPE = "authorization_code";
    private final String REDIRECT_URI = "http://localhost:8080/auth/kakao/callback";

    private final String PROPERTY_NICKNAME = "nickname";

    private final LoginValidator loginValidator;
    @GetMapping("/login")
    public String login(){
        return "loginForm.html";
    }

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String loginCallback(String code) {

        KakaoTokenDto.Request kakaoTokenRequset = KakaoTokenDto.Request.builder()
                .grant_type(GRANT_TYPE)
                .client_id(clientId)
                .redirect_uri(REDIRECT_URI)
                .code(code)
                .client_secret(clientSecret)
                .build();

        KakaoTokenDto.Response kakaoTokenResponse = kakaoTokenFeignClient.getKakaoToken(kakaoTokenRequset);

        return "kakao token : " + kakaoTokenResponse;
    }

    @GetMapping("/auth/kakao/userinfo")
    public @ResponseBody KakaoUserInfo getUserInfo(HttpServletRequest request) {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        KakaoUserInfo kakaoUserInfo =
                kakaoFeignClient.getUserInfo(authorizationHeader);

        String email = kakaoUserInfo.getKakaoAccount().getEmail();

        // 서비스에 연결하는 시점 카카오 계정 프로필을 복사하여 기본 제공 -> 2022.05.11 실시간 프로필 제공으로 변경
        Map<String, String> properties = kakaoUserInfo.getProperties();
        String nickname = properties.get(PROPERTY_NICKNAME);

        nickname = kakaoUserInfo.getKakaoAccount().getProfile().getNickname();

        return kakaoUserInfo;
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest httpServletRequest) throws AuthenticationException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        loginValidator.validateAuthorization(authorizationHeader);

        return ResponseEntity.ok().body("logout success");
    }
}
