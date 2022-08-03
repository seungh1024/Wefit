package com.ssafy.backend.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto {

    private String userGender;
    private String userName;
    private String userNickname;
    private String userField;
    private String userPhone;
    private boolean userConnect;
    private boolean userIsDeleted;
    private Date userCreated;
    private int userSingoCount;
    private long userId;
}
