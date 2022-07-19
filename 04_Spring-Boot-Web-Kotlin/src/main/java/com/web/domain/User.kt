package com.web.domain

import com.web.domain.enums.SocialType
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_info")

class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long = 0;

    @Column
    lateinit var name: String;

    @Column
    lateinit var password: String;

    @Column
    lateinit var email: String;

    lateinit var principal: String;

    @Enumerated(EnumType.STRING)
    lateinit var socialType: SocialType

    @Column
    lateinit var createdDate: LocalDateTime;

    @Column
    lateinit var updatedDate: LocalDateTime;

//    @Builder
//    constructor(name: String, password: String, email: String, createdDate: LocalDateTime, updatedDate: LocalDateTime) {
//        this.name = name;
//        this.password = password;
//        this.email = email;
//        this.createdDate = createdDate;
//        this.updatedDate = updatedDate;
//    }
}
