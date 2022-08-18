package com.ssafy.backend.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userDetail")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDetail {

    @Id
    @Column(name = "user_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userDetailId;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_field")
    private String userField;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_connect")
    private boolean userConnect;

    @Column(name = "user_isDeleted")
    private boolean userIsDeleted;

    @Column(name = "user_created")
    private boolean userCreated;

    @Column(name = "user_singo_count")
    private int userSingoCount;

    @Column(name = "user_MBTI")
    private String userMBTI;

    @Column(name = "user_temp_pwd")
    private String userTempPwd;



    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

}
