package kr.or.board.mapper;

import java.util.List;

import kr.or.board.vo.BoardVO;
import kr.or.board.vo.PagingVO;

public interface BoardMapper {

	List<BoardVO> boardList(PagingVO pagingVO);

	int getTotal(PagingVO paginVO);
	
	
	
}
