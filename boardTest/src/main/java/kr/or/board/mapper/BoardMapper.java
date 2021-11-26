package kr.or.board.mapper;

import java.util.List;
import java.util.Map;

import kr.or.board.vo.AtchFileVO;
import kr.or.board.vo.BoardVO;
import kr.or.board.vo.PagingVO;

public interface BoardMapper {

	List<BoardVO> boardList(PagingVO pagingVO);

	int getTotal(PagingVO paginVO);

	String getAtchFileNo();

	void atchFileUpload(AtchFileVO fileVO);

	void boardInsert(BoardVO boardVO);

	BoardVO boardDetail(String boardNo);

	List<AtchFileVO> fileDetail(String atchFileNo);

	void commentInsert(Map<String, Object> map);

	List<Map<String, Object>> commentList(String boardNo);

	int cmtDelete(String cmtNo);

	int boardDelete(String boardNo);

	void hitPlus(String boardNo);

	void boardUpdate(BoardVO boardVo);

	AtchFileVO filedown(AtchFileVO reqFileVO);
	
	
	
}
