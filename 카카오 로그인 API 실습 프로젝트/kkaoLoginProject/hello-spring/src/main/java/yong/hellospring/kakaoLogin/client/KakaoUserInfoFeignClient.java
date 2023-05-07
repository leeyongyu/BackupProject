package yong.hellospring.kakaoLogin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import yong.hellospring.kakaoLogin.dto.KakaoUserInfo;

@FeignClient(url = "https://kapi.kakao.com", name = "kakaoClient")
public interface KakaoUserInfoFeignClient {

    @GetMapping(value = "/v2/user/me", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
                                     , produces = MediaType.APPLICATION_JSON_VALUE)
    KakaoUserInfo getUserInfo(@RequestHeader("Authorization") String authorization);

}
