package kr.or.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.mapper.BoardMapper;
import kr.or.board.vo.BoardVO;
import kr.or.board.vo.PagingVO;

@Service
public class BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	public List<BoardVO> boardList(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return boardMapper.boardList(pagingVO);
	}

	public int getTotal(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return boardMapper.getTotal(pagingVO);
	}


}
