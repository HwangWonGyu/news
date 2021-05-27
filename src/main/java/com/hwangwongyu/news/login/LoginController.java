package com.hwangwongyu.news.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwangwongyu.news.redis.UserLoginInfo;
import com.hwangwongyu.news.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private UserLoginInfo userLoginInfo;

    @Value("${spring.webservice.newsHome}")
    private String newsHomeURL;

    private final LoginService loginService;

    private final ObjectMapper objectMapper;

    private final HttpSession httpSession;

    public LoginController(LoginService loginService, ObjectMapper objectMapper, HttpSession httpSession) {
        this.loginService = loginService;
        this.objectMapper = objectMapper;
        this.httpSession = httpSession;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginInfo userLoginInfo) {
        UserDTO loginUser = loginService.loginUser(userLoginInfo);
        if (loginUser == null) {
            return new ResponseEntity<>("계정정보가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        } else {
            if (httpSession.getAttribute(SessionKey.USER_LOGIN_INFO) != null) {
                return new ResponseEntity<>("이미 로그인된 상태입니다.", HttpStatus.OK);
            } else {
                httpSession.setAttribute(SessionKey.USER_LOGIN_INFO, userLoginInfo);
                return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
            }
        }
    }

    @PostMapping("/logout")
    public String logout() {
        loginService.logout();
        return "redirect:" + newsHomeURL;
    }

}

