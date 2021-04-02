package com.hwangwongyu.news.user;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @Getter
    @ToString
    public enum Sex {
        MALE,
        FEMALE
    }

    private String loginId;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private Sex sex;
    private String password;
    private String nickname;
    private String phoneNumber;
    private Boolean isReporter;
}

