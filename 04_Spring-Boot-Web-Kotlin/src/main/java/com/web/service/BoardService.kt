package com.web.service

import com.web.domain.Board
import com.web.repository.BoardRepository
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import java.util.Optional

@Service
class BoardService(
    private val boardRepository: BoardRepository
) {
    fun findBoardList(pageable: Pageable): Page<Board> {
        val pageable_ = PageRequest.of(if (pageable.pageNumber <= 0) 0 else pageable.pageNumber - 1, pageable.pageSize)
        return boardRepository.findAll(pageable_)
    }

    fun findBoardByIdx(idx: Long): Board {
        return boardRepository.findById(idx).orElse(Board())
    }
}
