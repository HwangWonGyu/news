package com.hwangwongyu.news.user;

import com.hwangwongyu.news.redis.UserLoginInfo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> allUsers() {
        return userMapper.allUsers();
    }

    @Override
    public Integer addUser(UserDTO user) throws DuplicateKeyException {
        UserDTO sameLoginIdUser = userMapper.findUserByLoginId(user.getLoginId());
        if (sameLoginIdUser != null) {
            throw new DuplicateKeyException("이미 등록된 아이디 입니다.");
        }

        UserDTO sameNicknameUser = userMapper.findUserByNickname(user.getNickname());
        if (sameNicknameUser != null) {
            throw new DuplicateKeyException("이미 등록된 닉네임 입니다.");
        }

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
    public UserDTO findUserByLoginId(String loginId) {
        return userMapper.findUserByLoginId(loginId);
    }

    @Override
    public UserDTO findUserByNickname(String nickname) {
        return userMapper.findUserByNickname(nickname);
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
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }

}
