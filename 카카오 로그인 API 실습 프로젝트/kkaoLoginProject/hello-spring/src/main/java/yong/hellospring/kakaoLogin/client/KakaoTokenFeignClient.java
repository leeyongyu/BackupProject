package yong.hellospring.kakaoLogin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import yong.hellospring.global.config.FeignConfig;
import yong.hellospring.kakaoLogin.dto.KakaoTokenDto;

@FeignClient(url = "https://kauth.kakao.com", name = "kakao-token", configuration = FeignConfig.class)
public interface KakaoTokenFeignClient {

        @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        KakaoTokenDto.Response getKakaoToken(@SpringQueryMap KakaoTokenDto.Request kakaoTokenRequest);

}
