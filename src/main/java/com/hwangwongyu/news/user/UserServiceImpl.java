package com.hwangwongyu.news.user;

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
}
