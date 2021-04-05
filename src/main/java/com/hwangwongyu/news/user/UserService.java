package com.hwangwongyu.news.user;

import com.hwangwongyu.news.redis.UserLoginInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    List<UserDTO> allUsers();

    Integer addUser(UserDTO user);

    Integer updateUser(UserDTO user);

    Integer deleteUser(String loginId);

    UserDTO findUserById(long id);

    UserDTO findUserByLoginId(String loginId);

    UserDTO findUserByNickname(String nickname);

    UserDTO loginUser(UserLoginInfo userLoginInfo);

    void logout(HttpSession httpSession);

}
