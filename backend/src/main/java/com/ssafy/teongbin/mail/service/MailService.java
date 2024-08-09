package com.ssafy.teongbin.mail.service;

import com.ssafy.teongbin.common.exception.CustomException;
import com.ssafy.teongbin.common.exception.ErrorType;
import com.ssafy.teongbin.common.jwt.PrincipalDetails;
import com.ssafy.teongbin.mail.dto.MailDto;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final RedisService redisService;

    private static String senderEmail = "teongbin@gmail.com";
    private static int number;

    public static void createNumber() {
        number = (int)(Math.random()*90000) + 100000;
    }

    public MimeMessage createMail(String mail) {
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public int sendMail(MailDto mailDto, PrincipalDetails userIn) {
        User user;

        Optional<User> ou = userRepository.findByEmail(userIn.getUsername());
        if ( ou.isPresent() ) {
            String mail = mailDto.getEmail().trim();
            MimeMessage message = createMail(mail);
            javaMailSender.send(message);
            redisService.saveVerificationCode(mail, String.valueOf(number));
            return number;
        } else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }
}
