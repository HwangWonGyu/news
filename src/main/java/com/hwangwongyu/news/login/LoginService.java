package com.hwangwongyu.news.login;

import com.hwangwongyu.news.redis.UserLoginInfo;
import com.hwangwongyu.news.user.UserDTO;

public interface LoginService {

    UserDTO loginUser(UserLoginInfo userLoginInfo);

    void logout();

    String getCurrentUserId();

}
