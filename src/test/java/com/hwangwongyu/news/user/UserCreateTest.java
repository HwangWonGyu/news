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

/*
 JUnit : 자바와 JVM 계열의 언어(예 : 코틀린)에서 사용하는 단위 테스트 프레임워크를 말한다.

 Mockito : 단위 테스트를 위한 자바 Mocking 프레임워크 중 하나이다.
 테스트 대역(Test Double)의 종류 중 모의(Mock) 객체를 필요로 할 때 사용한다.

 테스트 대역(Test Double) : 테스트하려는 객체와 연관된 객체를 사용하기가 어렵고 모호할 때 대신해 줄 수 있는 객체를 말한다.

 모의(Mock) 객체 : 호출했을 때 사전에 정의된 명세대로의 결과를 돌려주도록 미리 프로그램 돼있는 것이다.
 예상치 못한 호출이 있을 경우 예외를 던질 수 있으며 모든 호출이 예상된 것이었는지 확인할 수 있다.
 */

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
