package com.hwangwongyu.news.user;

import java.util.List;

public interface UserService {

    List<UserDTO> allUsers();

    Integer addUser(UserDTO user);

    Integer updateUser(UserDTO user);

    Integer deleteUser(String loginId);

    UserDTO findUserById(long id);

    UserDTO findUserByLoginId(String loginId);

    UserDTO findUserByNickname(String nickname);

    Boolean sendEmailAuthNCode(String toEmail);

    List<String> allCompanies();
}
