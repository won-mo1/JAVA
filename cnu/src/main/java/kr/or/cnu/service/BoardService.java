package kr.or.cnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.cnu.dao.BoardDao;
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
		return boardDao.boardDetail(boardNo); //�ۼ��� ����
	}

	public int total(PagingVO pagingVO) {
		return boardDao.total(pagingVO);
	}

	public int commentInsert(Map<String, Object> map) {
		return boardDao.commentInsert(map);
	}

}
