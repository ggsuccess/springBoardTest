package com.springBoard.demo.controller;

import com.springBoard.demo.entity.Board;
import com.springBoard.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") //localhost:8080/board/write
    public String boardWriteForm() {
        return "boardWrite";
    }

    @PostMapping("/board/writedo")
    public String boardWriteDo(Board board) {
        boardService.write(board);
        return "redirect:/board/list";
    }

    @GetMapping("/board/list")
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {
        Page<Board> list = null;
        if(searchKeyword == null) {
            list = boardService.boardList(pageable);
        } else {
            list = boardService.boardSearchList(searchKeyword,pageable);
        }

        int currentPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(currentPage - 4, 1);
        int endPage = Math.min(currentPage + 5, list.getTotalPages());
        model.addAttribute("list", list);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "boardList";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) {
        Board board1 = boardService.boardView(id);
        board1.addViewCount();
        boardService.write(board1);
        model.addAttribute("board", boardService.boardView(id));
        return "boardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete(int id) {
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") int id, Model model) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardModify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") int id, Board board) {
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardService.write(boardTemp);
        return "redirect:/board/list";
    }
}
