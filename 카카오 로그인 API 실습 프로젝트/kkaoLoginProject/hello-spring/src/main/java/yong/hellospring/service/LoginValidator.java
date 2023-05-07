package yong.hellospring.service;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LoginValidator {

    public void validateAuthorization(String authorizationHeader) throws AuthenticationException {
        // authorization 필수 체크
        if(!StringUtils.hasText(authorizationHeader)){
            throw new AuthenticationException("Authorization 헤더가 존재하지 않습니다.");
        }
        // authorization Bearer 체크
        String [] authorizations = authorizationHeader.split(" ");
        if(authorizations.length < 2){
            throw new AuthenticationException("Bearer이 존재하지 않습니다.");
        }
    }
}
