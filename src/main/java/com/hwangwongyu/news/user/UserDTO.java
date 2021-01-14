package com.hwangwongyu.news.user;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserDTO {

    @Getter
    @ToString
    public enum Sex {
        MALE,
        FEMALE
    }

    String loginId;
    String name;
    LocalDate birthDate;
    Sex sex;
    String password;
    String nickname;
    String phoneNumber;

    @Builder
    private UserDTO(String loginId, String name, LocalDate birthDate, Sex sex, String password, String nickname, String phoneNumber) {
        this.loginId = loginId;
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }
}
