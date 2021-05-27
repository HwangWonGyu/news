package com.hwangwongyu.news.login;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
@Aspect
public class CheckLoginAspect {

    private final LoginService loginService;

    public CheckLoginAspect (LoginService loginService) {
        this.loginService = loginService;
    }

    @Before("@annotation(com.hwangwongyu.news.login.CheckLogin)")
    public void checkLogin() throws HttpClientErrorException {
        String currentUserId = loginService.getCurrentUserId();

        if (currentUserId == null) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }

}
