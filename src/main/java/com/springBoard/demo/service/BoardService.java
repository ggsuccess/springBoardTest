package com.springBoard.demo.service;

import com.springBoard.demo.entity.Board;
import com.springBoard.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    public void write(Board board) {//글 작성
        boardRepository.save(board);
    }

    public Page<Board> boardList(Pageable pageable) {//게시물 리스트 보여주기
        return boardRepository.findAll(pageable);
    }

    public Page<Board> boardSearchList(String searchKeyword,Pageable pageable) {
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }
    public Board boardView(Integer id) { // 게시물 상세 보기
        
        return boardRepository.findById(id).get();
    }

    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }
}
