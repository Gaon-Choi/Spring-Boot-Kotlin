package com.havi.bookControllerTest

import com.havi.controller.BookController
import com.havi.domain.Book
import com.havi.service.BookService
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import java.time.LocalDateTime
import java.util.Collections

@RunWith(SpringRunner::class)
@WebMvcTest(BookController::class)
class BookControllerTest {
    @MockBean
    private lateinit var bookService: BookService

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun Book_MVC_test() {
        // FIXME
        val book = Book("Spring Boot Book", LocalDateTime.now())

        given(bookService.getBookList())
            .willReturn(Collections.singletonList(book))

        mvc.perform(get("/books"))
            // HTTP 상태값이 200인지 테스트
            .andExpect(status().isOk)
            // 반환되는 뷰의 이름이 "book"인지 테스트
            .andExpect(view().name("book"))
            // 모델 프로퍼티 중 'bookList'라는 프로퍼티가 존재하는지 테스트
            .andExpect(model().attributeExists("bookList"))
            // 위에서 말한 프로퍼티 속에 book 객체가 있는지를 테스트
            .andExpect(model().attribute("bookList", contains(book)))
    }
}
