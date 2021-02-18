package com.hwangwongyu.news.user;

import com.hwangwongyu.news.redis.UserLoginInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserDTO> allUsers();

    Integer addUser(UserDTO user);

    Integer updateUser(UserDTO user);

    Integer deleteUser(String loginId);

    UserDTO findUserById(long id);

    String getPassword(String loginId);

    UserDTO findUser(UserLoginInfo userLoginInfo);
}
