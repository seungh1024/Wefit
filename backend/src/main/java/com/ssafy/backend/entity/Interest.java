package com.ssafy.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "interest")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Interest {

    @Id
    @Column(name = "interest_name", length = 50)
    private String InterestName;
}
