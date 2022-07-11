package com.havi.bookJpaTest

import com.havi.domain.Book
import com.havi.repository.BookRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.collection.IsEmptyCollection
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@DataJpaTest
class BookJpaTest {
    companion object {
        const val BOOT_TEST_TITLE = "Spring Boot Test Book"
    }

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Test
    fun bookSaveTest() {
        // FIXME
        val book = Book(BOOT_TEST_TITLE, LocalDateTime.now())

        bookRepository.save(book)
        testEntityManager.persist(book)
        assertThat(bookRepository.findById(book.idx)
            .get(), `is`(book))
    }

    /*
    아래 테스트 시 빌드 오류가 발생한다면...

    Settings -> Build, Execution, Deployment -> Build Tools -> Gradle
    Build and run using -> Run tests using == Intellij IDEA
    */


    @Test
    fun bookListSaveAndSearchTest() {
        // FIXME
        val book_list = ArrayList<Book>()
        for (i in 1 .. 3) {
            val new_book = Book(BOOT_TEST_TITLE + i, LocalDateTime.now())
            book_list.add(new_book)
            bookRepository.save(new_book)
        }

        val bookList = bookRepository.findAll()
        assertThat(bookList, hasSize(3))
        assertThat(bookList, contains(book_list[0], book_list[1], book_list[2]))
    }

    @Test
    fun bookListSaveAndDeleteTest() {
        // FIXME
        // save 부분은 bookListSaveAndSearchTest의 부분과 동일
        val book_list = ArrayList<Book>()
        for (i in 1 .. 3) {
            val new_book = Book(BOOT_TEST_TITLE + i, LocalDateTime.now())
            book_list.add(new_book)
            bookRepository.save(new_book)
        }

        // 삭제
        val idx_to_be_deleted = 0   // delete the first element
        bookRepository.deleteById(book_list[idx_to_be_deleted].idx)

        val bookList = bookRepository.findAll()
        assertThat(bookList, hasSize(2))    // 3개 중 하나 지웠으니 2개

        val noValue = bookRepository.findById(book_list[0].idx)
        assertThat(noValue.orElse(null), nullValue())
    }
}
