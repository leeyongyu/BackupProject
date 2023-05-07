package yong.hellospring.kakaoLogin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public class KakaoTokenDto {

    @Getter
    @Setter
    public static class Request {
        private String grant_type;
        private String client_id;
        private String redirect_uri;
        private String code;
        private String client_secret;
        private String email;
        private String refresh_token;

        @Builder
        public Request(String grant_type, String client_id, String redirect_uri,
                       String code, String client_secret, String email,
                       String refresh_token) {
            this.grant_type = grant_type;
            this.client_id = client_id;
            this.redirect_uri = redirect_uri;
            this.code = code;
            this.client_secret = client_secret;
            this.email = email;
            this.refresh_token = refresh_token;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Response {
        private String token_type;
        private String access_token;
        private String id_token;
        private Integer expires_in;
        private String refresh_token;
        private Integer refresh_token_expires_in;
        private String scope;
    }
}
