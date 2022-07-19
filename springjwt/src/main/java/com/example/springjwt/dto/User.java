package com.example.springjwt.dto;

import com.example.springjwt.enums.RoleType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
