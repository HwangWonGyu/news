package com.hwangwongyu.news.user;

import com.hwangwongyu.news.redis.UserLoginInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final JavaMailSender mailSender;

    @Value("${spring.mail.auth-digit-number-size}")
    private Integer authDigitNumberSize;

    public UserServiceImpl(UserMapper userMapper, JavaMailSender mailSender) {
        this.userMapper = userMapper;
        this.mailSender = mailSender;
    }

    @Override
    public List<UserDTO> allUsers() {
        return userMapper.allUsers();
    }

    @Override
    public Integer addUser(UserDTO user) {
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

    @Override
    public Boolean emailAuth(String toEmail) {

        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail));
            mimeMessage.setSubject("뉴스 서비스 - 기자 인증코드");
            mimeMessage.setText(randomAuthCode(authDigitNumberSize));
        };

        // 인증 O, X 여부를 사용자 테이블에 TRUE/FALSE로 저장하는 구조로 변경할 것

        try {
            this.mailSender.send(preparator);
            return true;
        }
        catch (MailException ex) {
            return false;
            // 예외 관리를 한곳에서 용이하게 하기 위해 Global level에서 처리하는 것으로 개선
            // 에러 로깅 시스템 도입 작업시 이 부분을 로깅 도입으로 개선
        }
    }

    private String randomAuthCode(int size) {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder stringBuilder = new StringBuilder();
        int num = 0;

        while(stringBuilder.length() < size) {
            num = random.nextInt(10);
            stringBuilder.append(num);
        }

        return stringBuilder.toString();
    }

    @Override
    public List<String> allCompanies() {
        return userMapper.allCompanies();
    }


}
