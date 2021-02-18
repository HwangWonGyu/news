package com.hwangwongyu.news.redis;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Getter
@Setter
@Component
@SessionScope
@ToString
public class UserLoginInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String loginId;
    private String password;

}
