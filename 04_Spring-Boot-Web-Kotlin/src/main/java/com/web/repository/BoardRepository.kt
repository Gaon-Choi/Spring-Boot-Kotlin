package com.web.repository

import com.web.domain.User
import com.web.domain.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long?>{
    fun findByUser(user: User): Board
}
