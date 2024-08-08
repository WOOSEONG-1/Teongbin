package com.ssafy.teongbin.mail.service;

import com.ssafy.teongbin.common.jwt.PrincipalDetails;
import com.ssafy.teongbin.mail.dto.MailDto;
import com.ssafy.teongbin.mail.dto.request.ApproveRequestDto;
import com.ssafy.teongbin.mail.entity.Code;
import com.ssafy.teongbin.mail.repository.CodeRepository;
import com.ssafy.teongbin.user.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final CodeRepository codeRepository;

    private static String senderEmail = "c2824463@gmail.com";
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
        String mail = mailDto.getMail().trim();
        MimeMessage message = createMail(mail);
        Code code = new Code();
        code.setCodenumber(number);
        code.setUser(userIn.getUser());
        codeRepository.save(code);

        javaMailSender.send(message);

        return number;
    }

    public boolean approve(ApproveRequestDto approveRequestDto, PrincipalDetails userIn) {
        User user = userIn.getUser();
        if (approveRequestDto.getCode()==user.getCodes().get(0).getCodenumber()) {
            return true;
        } else {
            return false;
        }
    }
}
