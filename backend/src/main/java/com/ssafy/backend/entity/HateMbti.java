package com.ssafy.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "hate_mbti")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HateMbti {

    @Id
    @Column(name = "hate_mbti_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hateMbtiId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name= "mbti_name")
    private Mbti mbtiName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_detail_id")
    private UserDetail userDetail;

}
