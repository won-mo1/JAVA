package kr.or.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.board.service.BoardService;
import kr.or.board.vo.BoardVO;
import kr.or.board.vo.PagingVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/main.do")
	public String boardMain(Model model
							, @ModelAttribute PagingVO pagingVO
							, @RequestParam (defaultValue = "1") String nowPage) {
		
		int boardTotal = boardService.getTotal(pagingVO);
		
		pagingVO = new PagingVO(boardTotal, Integer.parseInt(nowPage), 10, pagingVO.getSearchFlag(), pagingVO.getSearchKeyword());
		
		List<BoardVO>boardList = boardService.boardList(pagingVO);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/boardMain";
	}
	
}
