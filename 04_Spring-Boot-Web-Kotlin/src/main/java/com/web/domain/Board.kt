package com.web.domain

import com.web.domain.enums.BoardType

import java.io.Serializable
import java.time.LocalDateTime

import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import javax.persistence.*

@Getter
@NoArgsConstructor
@Entity
@Table
class Board {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long = 0

    @Column
    var title: String = ""

    @Column
    var subTitle: String = ""

    @Column
    var content: String = ""

    @Column
    @Enumerated(EnumType.STRING)
    var boardType: BoardType = BoardType.free

    @Column
    var createdDate: LocalDateTime = LocalDateTime.now()

    @Column
    var updatedDate: LocalDateTime = LocalDateTime.now()

    @OneToOne(fetch = FetchType.LAZY)
    lateinit var user: User

//    @Builder
//    constructor(title: String, subTitle: String, content: String, boardType: BoardType, createdDate: LocalDateTime, updatedDate: LocalDateTime, user: User) {
//        this.title = title
//        this.subTitle = subTitle
//        this.content = content
//        this.boardType = boardType
//        this.createdDate = createdDate
//        this.updatedDate = updatedDate
//        this.user = user
//    }
}
