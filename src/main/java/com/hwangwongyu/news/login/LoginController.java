package com.hwangwongyu.news.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwangwongyu.news.redis.UserLoginInfo;
import com.hwangwongyu.news.user.UserDTO;
import com.hwangwongyu.news.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    private UserLoginInfo userLoginInfo;

    @Autowired
    private RedisTemplate<String, UserLoginInfo> userLoginInfoRedisTemplate;

    private final UserService userService;

    private final ObjectMapper objectMapper;

    @Autowired
    public LoginController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginInfo userLoginInfo) {
        UserDTO loginUser = userService.loginUser(userLoginInfo);
        if (loginUser == null) {
            return new ResponseEntity<>("계정정보가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        } else {
            userLoginInfoRedisTemplate.opsForHash().put("user", "1", userLoginInfo);
            return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
        }
    }


}

