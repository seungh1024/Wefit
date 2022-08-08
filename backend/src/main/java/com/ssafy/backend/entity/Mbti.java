package com.ssafy.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Mbti")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mbti {

    @Id
    @Column(name = "mbti_name")
    private String mbtiName;
}
