package com.hwangwongyu.news.user;

import java.util.List;

public interface UserService {

    List<UserDTO> allUsers();

    Integer addUser(UserDTO user);

    Integer updateUser(UserDTO user);

    Integer deleteUser(String loginId);

    UserDTO findUserById(long id);
}
