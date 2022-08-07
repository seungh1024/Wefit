package com.ssafy.backend.dto;

import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDto {

    @NotNull
    private String password;

    @NotNull
    private String passwordCheck;
}
