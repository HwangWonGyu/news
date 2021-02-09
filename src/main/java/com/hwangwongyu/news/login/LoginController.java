package com.hwangwongyu.news.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwangwongyu.news.redis.UserLoginInfo;
import com.hwangwongyu.news.user.UserDTO;
import com.hwangwongyu.news.user.UserService;
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

    private final UserService userService;

    private final ObjectMapper objectMapper;

    public LoginController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginInfo userLoginInfo, HttpSession httpSession) {
        UserDTO loginUser = userService.loginUser(userLoginInfo);
        if (loginUser == null) {
            return new ResponseEntity<>("계정정보가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        } else {
            if (httpSession.getAttribute("userLoginInfo") != null) {
                return new ResponseEntity<>("이미 로그인된 상태입니다.", HttpStatus.OK);
            } else {
                httpSession.setAttribute("userLoginInfo", userLoginInfo);
                return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
            }
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession httpSession) {
        userService.logout(httpSession);
        return "redirect:" + newsHomeURL;
    }

}

