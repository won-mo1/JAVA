package kr.or.cnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.cnu.dao.BoardDao;
import kr.or.cnu.vo.AtchFileVO;
import kr.or.cnu.vo.BoardVO;
import kr.or.cnu.vo.PagingVO;

@Service
public class BoardService {

	@Autowired
	BoardDao boardDao;
	
	public List<BoardVO> boardList(PagingVO pagingVO) {
		
		return boardDao.boardList(pagingVO);
	}

	public BoardVO boardDetail(String boardNo) {
		// TODO Auto-generated method stub
		return boardDao.boardDetail(boardNo); //작성후 생성
	}

	public int total(PagingVO pagingVO) {
		return boardDao.total(pagingVO);
	}

	public int commentInsert(Map<String, Object> map) {
		return boardDao.commentInsert(map);
	}

	public String atchFileNo() {
		// TODO Auto-generated method stub
		return boardDao.atchFileNo();
	}

	public void atchFile(AtchFileVO fileVo) {
		boardDao.atchFile(fileVo);
		
	}

	public List<Map<String, Object>> commentList(String boardNo) {
		// TODO Auto-generated method stub
		return boardDao.commentList(boardNo);
	}

	public int commentDelete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardDao.commentDelete(map);
	}

}
