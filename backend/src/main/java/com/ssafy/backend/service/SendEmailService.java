package com.ssafy.backend.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.ssafy.backend.dto.MailDto;
import com.ssafy.backend.entity.QUser;
import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.UserRepository;
import com.ssafy.backend.util.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.InternetAddressEditor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class SendEmailService {

    @Autowired
    UserRepository userRepository;

    private JavaMailSender mailSender;
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    private static final String FROM_ADDRESS ="ssafy07206@gmail.com";

    public SendEmailService(JavaMailSender mailSender, EntityManager em) {
        this.mailSender = mailSender;
        this.em = em;
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Transactional
    public MailDto createMailAndChangePassword(String userEmail){
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(userEmail);
        dto.setTitle(userEmail+"님의 WeFit 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. WeFit 임시비밀번호 안내 관련 이메일 입니다." + "[" + userEmail + "]" +"님의 임시 비밀번호는 "
                + str + " 입니다.");
        updatePassword(str,userEmail);
        return dto;
    }
    @Transactional
    public void updatePassword(String str,String userEmail){
        String pw = EncryptionUtils.encryptMD5(str);
        User user = userRepository.findUserByUserEmail(userEmail);
        QUser qUser = QUser.user;

        JPAUpdateClause updateClause = new JPAUpdateClause(em, qUser);
        updateClause.where(qUser.userEmail.eq(userEmail))
                .set(qUser.userPassword,pw)
                .execute();
    }


    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void mailSend(MailDto mailDto){
        System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        String from = FROM_ADDRESS.toString().replaceAll("\\s", "");
        message.setTo(mailDto.getAddress());
        System.out.println(mailDto.getAddress());
        message.setFrom(from);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        System.out.println(mailSender);
        mailSender.send(message);

    }

}
