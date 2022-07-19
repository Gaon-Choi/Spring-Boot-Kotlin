package com.web

import com.web.domain.Board
import com.web.domain.User
import com.web.domain.enums.BoardType
import com.web.repository.BoardRepository
import com.web.repository.UserRepository
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@DataJpaTest
open class JpaMappingTest {
    private val boardTestTitle = "테스트"
    private val email = "test@gmail.com"

    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    var boardRepository: BoardRepository? = null
    @Before
    fun init() {
        val user: User = userRepository!!.save(
            User().also {
                it.name = "havi"
                it.password = "test"
                it.email = email
                it.createdDate = LocalDateTime.now()
            }
        )
        boardRepository!!.save(
            Board().also {
                it.title = boardTestTitle
                it.subTitle = "서브 타이틀"
                it.content = "컨텐츠"
                it.boardType = BoardType.free
                it.createdDate = LocalDateTime.now()
                it.updatedDate = LocalDateTime.now()
            })
    }

    @Test
    fun 제대로_생성_됐는지_테스트() {
        val user = userRepository!!.findByEmail(email)
        MatcherAssert.assertThat(user.name, Is.`is`("havi"))
        MatcherAssert.assertThat(user.password, Is.`is`("test"))
        MatcherAssert.assertThat(user.email, Is.`is`(email))
        val board = boardRepository!!.findByUser(user)
        MatcherAssert.assertThat(board!!.title, Is.`is`(boardTestTitle))
        MatcherAssert.assertThat(board.subTitle, Is.`is`("서브 타이틀"))
        MatcherAssert.assertThat(board.content, Is.`is`("컨텐츠"))
        MatcherAssert.assertThat(board.boardType, Is.`is`(BoardType.free))
    }
}
