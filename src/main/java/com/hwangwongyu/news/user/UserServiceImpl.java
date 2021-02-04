package com.hwangwongyu.news.user;

import com.hwangwongyu.news.redis.UserLoginInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    UserServiceImpl(UserMapper userMapper)
    {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> allUsers() {
        return userMapper.allUsers();
    }

    @Override
    public Integer addUser(UserDTO user)
    {
        return userMapper.addUser(user);
    }

    @Override
    public Integer updateUser(UserDTO user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Integer deleteUser(String loginId) {
        return userMapper.deleteUser(loginId);
    }

    @Override
    public UserDTO findUserById(long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public UserDTO loginUser(UserLoginInfo userLoginInfo) {
        String password = userMapper.getPassword(userLoginInfo.getLoginId());

        if (password == null || userLoginInfo.getPassword().equals(password) == false ) {
            return null;
        }

        UserDTO userDTO = userMapper.findUser(userLoginInfo);
        return userDTO;
    }

}
