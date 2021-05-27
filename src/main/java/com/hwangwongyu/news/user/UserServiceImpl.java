package com.hwangwongyu.news.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final JavaMailSender mailSender;

    @Value("${spring.mail.auth-digit-number-size}")
    private Integer authNDigitNumberSize;

    public UserServiceImpl(UserMapper userMapper, JavaMailSender mailSender) {
        this.userMapper = userMapper;
        this.mailSender = mailSender;
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
    public Boolean sendEmailAuthNCode(String toEmail) {

        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail));
            mimeMessage.setSubject("뉴스 서비스 - 기자 인증코드");
            mimeMessage.setText(randomAuthNCode(authNDigitNumberSize));
        };

        try {
            this.mailSender.send(preparator);
            return true;
        }
        // 아래 Exception들의 상위 클래스 : MailException
        catch (MailSendException ex) {
            // 대표적으로는
            // 1. 보안설정, 백신, 방화벽 때문에
            // 2. SSL 보안수준의 앱이지만 관련 포트를 사용하지 않아서
            // 3. 잘못된 포트를 사용해서
            // 메일 서버 연결에 실패했을때 발생
            return false;
        }
        catch (MailAuthenticationException ex) {
            // 지금 개발중인 이 앱은 gmail SMTP 서버 입장에서는 '보안 수준이 낮은 앱'으로 분류되는데
            // SMTP 서버에 사용될 계정에서 보안 수준이 낮은 앱과 관련된 설정값에 따라 메일 송신 허용여부가 결정됨
            // 이때 비허용이라면 발생
            return false;
        }
        catch (MailParseException ex ) {
            // 메일 파서(Parser)가 MimeMessage, InputStream 등 메일 객체로 파싱 중 실패했을때 발생
            return false;
        }
        catch (MailPreparationException ex ) {
            // HTML, 템플릿 엔진(예 : Thymeleaf) 등에서 메일 메시지를 렌더링하는데에 실패했을때 발생
            return false;
        }

    }

    private String randomAuthNCode(int authNDigitNumberSize) {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder stringBuilder = new StringBuilder();
        int num = 0;

        while(stringBuilder.length() < authNDigitNumberSize) { // 파라미터인 '자리수' 도달까지 루프
            num = random.nextInt(10); // 0 ~ bound-1 범위 정수 추출
            stringBuilder.append(num);
        }

        return stringBuilder.toString();
    }

    @Override
    public List<String> allCompanies() {
        return userMapper.allCompanies();
    }

}
