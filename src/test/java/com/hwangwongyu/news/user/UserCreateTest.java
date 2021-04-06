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

    private UserDTO UserDTOAllField;
    private UserDTO UserDTORequiredField;

    @BeforeEach
    void init() {
        UserDTOAllField = UserDTO.builder().
                loginId("loginid123").
                name("황사이다").
                birthDate(LocalDate.of(2000,11,11)).
                sex(UserDTO.Sex.MALE).
                password("비1밀2번3호").
                nickname("닉네임123이다").
                phoneNumber("01012345678").
                build();

        UserDTORequiredField = UserDTO.builder().
                loginId("loginid456").
                name("황환타").
                birthDate(LocalDate.of(2000,12,12)).
                sex(UserDTO.Sex.FEMALE).
                password("비밀번호456").
                nickname("닉네임456이다").
                build();
    }

    @Test
    @DisplayName("모든 UserDTO 데이터가 입력된 경우 유저 등록 성공")
    void SuccessUserCreateIfAllFieldInserted() {
        assertDoesNotThrow(() -> userServiceImpl.addUser(UserDTOAllField));
    }

    @Test
    @DisplayName("필수 입력필드만 UserDTO 데이터에 입력된 경우도 유저 등록 성공")
    void SuccessUserCreateIfRequiredFieldInserted() {
        assertDoesNotThrow(() -> userServiceImpl.addUser(UserDTORequiredField));
    }

    @Test
    @DisplayName("아이디 중복으로 인한 유저 등록 실패")
    public void FailToUserCreateIfDuplicateLoginId() throws Exception {
        given(userServiceImpl.findUserByLoginId("loginid123")).willReturn(UserDTOAllField);

        assertThrows(DuplicateKeyException.class,
            () -> {
                userServiceImpl.addUser(UserDTOAllField);
            }
        );
    }

    @Test
    @DisplayName("닉네임 중복으로 인한 유저 등록 실패")
    void FailToUserCreateIfDuplicateNickname() {
        given(userServiceImpl.findUserByNickname("닉네임123이다")).willReturn(UserDTOAllField);

        assertThrows(DuplicateKeyException.class,
                () -> {
                    userServiceImpl.addUser(UserDTOAllField);
                }
        );
    }
}
