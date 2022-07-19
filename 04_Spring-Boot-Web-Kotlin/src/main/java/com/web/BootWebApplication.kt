package com.web

import com.web.domain.Board
import com.web.domain.enums.BoardType
import com.web.repository.BoardRepository
import com.web.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import com.web.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.time.LocalDateTime

@SpringBootApplication
open class BootWebApplication : WebMvcConfigurerAdapter() {

//    @Autowired
//    private lateinit var userArgumentResolver: UserArgumentResolver
//
//    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
//        argumentResolvers.add(userArgumentResolver)
//    }

    @Bean
    open fun runner(userRepository: UserRepository, boardRepository: BoardRepository) : CommandLineRunner = CommandLineRunner {
        val user = userRepository.save(User().apply {
            name = "choigaon1028"
            password = "chl10280"
            email = "choigaon1028@naver.com"
            createdDate = LocalDateTime.now()
        })

        for(index in 1 .. 200) {
            boardRepository.save(Board().apply {
                title = "게시글" + index
                subTitle = "순서" + index
                content = "콘텐츠"
                boardType = BoardType.free
                createdDate = LocalDateTime.now()
                updatedDate = LocalDateTime.now()
                this.user = user
            })
        }
    }
}

fun main(args: Array<String>) {
    runApplication<BootWebApplication>(*args)
}
