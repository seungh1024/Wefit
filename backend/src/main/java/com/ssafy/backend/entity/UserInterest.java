package com.ssafy.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_interest")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInterest {

    @Id
    @Column(name = "user_interest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userInterestId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name= "interest_name")
    private Interest interest;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_detail_id")
    private UserDetail userDetail;

}
