package kr.or.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.mapper.BoardMapper;
import kr.or.board.vo.AtchFileVO;
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

	public String getAtchFileNo() {
		// TODO Auto-generated method stub
		return boardMapper.getAtchFileNo();
	}

	public void atchFileUpload(AtchFileVO fileVO) {
		// TODO Auto-generated method stub
		boardMapper.atchFileUpload(fileVO);
	}

	public void boardInsert(BoardVO boardVO) {
		// TODO Auto-generated method stub
		boardMapper.boardInsert(boardVO);
	}

	public BoardVO boardDetail(String boardNo) {
		// TODO Auto-generated method stub
		return boardMapper.boardDetail(boardNo);
	}

	public List<AtchFileVO> fileDetail(String atchFileNo) {
		// TODO Auto-generated method stub
		return boardMapper.fileDetail(atchFileNo);
	}

	public void commentInsert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		map.put("commentNo", "");
		boardMapper.commentInsert(map);
	}

	public List<Map<String, Object>> commentList(String boardNo) {
		// TODO Auto-generated method stub
		return boardMapper.commentList(boardNo);
	}

	public int cmtDelete(String cmtNo) {
		// TODO Auto-generated method stub
		return boardMapper.cmtDelete(cmtNo);
	}

	public int boardDelete(String boardNo) {
		// TODO Auto-generated method stub
		return boardMapper.boardDelete(boardNo);
	}

	public void hitPlus(String boardNo) {
		// TODO Auto-generated method stub
		boardMapper.hitPlus(boardNo);
	}

	public void boardUpdate(BoardVO boardVo) {
		// TODO Auto-generated method stub
		boardMapper.boardUpdate(boardVo);
	}

	public AtchFileVO filedown(AtchFileVO reqFileVO) {
		// TODO Auto-generated method stub
		return boardMapper.filedown(reqFileVO);
	}


}
