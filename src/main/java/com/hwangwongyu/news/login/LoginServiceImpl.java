package com.hwangwongyu.news.login;

import com.hwangwongyu.news.redis.UserLoginInfo;
import com.hwangwongyu.news.user.UserDTO;
import com.hwangwongyu.news.user.UserMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserMapper userMapper;

    private final HttpSession httpSession;

    public LoginServiceImpl(UserMapper userMapper, HttpSession httpSession) {
        this.userMapper = userMapper;
        this.httpSession = httpSession;
    }


    @Override
    public UserDTO loginUser(UserLoginInfo userLoginInfo) {

        String matchedPassword =
                Optional.ofNullable(userMapper.getPassword(userLoginInfo.getLoginId()))
                        .filter(o -> o.equals(userLoginInfo.getPassword()))
                        .orElse("");

        if (matchedPassword.isEmpty()) {
            return null;
        } else {
            return userMapper.findUser(userLoginInfo);
        }

    }

    @Override
    public void logout() {
        httpSession.invalidate();
    }

    @Override
    public String getCurrentUserId() {
        Object session = httpSession.getAttribute(SessionKey.USER_LOGIN_INFO);
        if(session == null) {
            return "";
        }

        UserLoginInfo loginInfo = (UserLoginInfo)session;
        return loginInfo.getLoginId();
    }

}
