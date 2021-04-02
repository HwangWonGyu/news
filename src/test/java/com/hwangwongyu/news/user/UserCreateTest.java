package com.hwangwongyu.news.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserCreateTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private UserDTO userDTO;

    @BeforeEach
    void init() {
        userDTO = UserDTO.builder().
                loginId("loginid123").
                name("황사이다").
                birthDate(LocalDate.of(2000,11,11)).
                sex(UserDTO.Sex.MALE).
                password("비1밀2번3호").
                nickname("닉네임123이다").
                phoneNumber("01012345678").
                isReporter(false).
                build();
    }

    @Test
    @DisplayName("유저 등록 성공")
    void SuccessUserCreate() {
        assertDoesNotThrow(() -> userServiceImpl.addUser(userDTO));
    }

    @Test
    @DisplayName("아이디 중복으로 인한 유저 등록 실패")
    public void FailToUserCreateIfDuplicateLoginId() throws Exception {
        given(userServiceImpl.findUserByLoginId("loginid123")).willReturn(userDTO);

        assertThrows(DuplicateKeyException.class,
            () -> {
                userServiceImpl.addUser(userDTO);
            }
        );
    }

    @Test
    @DisplayName("닉네임 중복으로 인한 유저 등록 실패")
    void FailToUserCreateIfDuplicateNickname() {
        given(userServiceImpl.findUserByNickname("닉네임123이다")).willReturn(userDTO);

        assertThrows(DuplicateKeyException.class,
                () -> {
                    userServiceImpl.addUser(userDTO);
                }
        );
    }
}
