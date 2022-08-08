package com.ssafy.backend.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;

import com.ssafy.backend.entity.QUserDetail;

import com.ssafy.backend.entity.User;
import com.ssafy.backend.entity.UserDetail;
import com.ssafy.backend.repository.UserDetailRepository;
import com.ssafy.backend.repository.UserRepository;

import com.ssafy.backend.vo.MailVo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional
@RequiredArgsConstructor

public class MailService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final JavaMailSender mailSender;
    private final EntityManager em;
    //private final JPAQueryFactory jpaQueryFactory;
    private static final String title = "WeFit 임시 비밀번호 안내 이메일입니다.";
    private static final String message = "안녕하세요. Wefit 임시 비밀번호 안내 메일입니다. "
            +"\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요."+"\n";
    private static final String fromAddress = "ssafy07206@gmail.com";



    // 이메일 생성

    public MailVo createMail(String userEmail) {
        String tmpPassword = getTempPassword();
        insertTempPassword(tmpPassword,userEmail);
        MailVo mailVo = MailVo.builder()
                .toAddress(userEmail)
                .title(title)
                .message(message + tmpPassword)
                .fromAddress(fromAddress)
                .build();
        return mailVo;
    }

    // 이메일 전송
    @Transactional
    public void sendMail(MailVo mailVo) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mailVo.getToAddress());
        mailMessage.setSubject(mailVo.getTitle());
        mailMessage.setText(mailVo.getMessage());
        mailMessage.setFrom(mailVo.getFromAddress());
        mailMessage.setReplyTo(mailVo.getFromAddress());

        mailSender.send(mailMessage);

    }

    @Transactional
    public void insertTempPassword(String str,String userEmail){
       // String pw = EncryptionUtils.encryptMD5(str);
        User user = userRepository.findUserByUserEmail(userEmail);
        UserDetail userDetail = userDetailRepository.findUserDetailByUser(user);
        QUserDetail qUserDetail = QUserDetail.userDetail;
        JPAUpdateClause updateClause = new JPAUpdateClause(em, qUserDetail);
        updateClause.where(qUserDetail.userDetailId.eq(userDetail.getUserDetailId()))
                .set(qUserDetail.userTempPwd,str)
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
}