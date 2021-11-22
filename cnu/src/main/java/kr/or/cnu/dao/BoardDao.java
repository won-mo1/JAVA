package kr.or.cnu.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.cnu.vo.AtchFileVO;
import kr.or.cnu.vo.BoardVO;
import kr.or.cnu.vo.PagingVO;

@Repository
public class BoardDao {

	@Autowired
	SqlSessionTemplate sessionTemplate;

	public List<BoardVO> boardList(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return sessionTemplate.selectList("board.main", pagingVO);
	}

	public BoardVO boardDetail(String boardNo) {
		// TODO Auto-generated method stub
		return sessionTemplate.selectOne("board.detail", boardNo); //셀렉트 하나만 하겠음
			// 마이바티스 쿼리문수행. 셀렉트 하나만 ("xml이름의 실행할이름", 파라미터)
	}

	public int total(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return sessionTemplate.selectOne("board.total", pagingVO);
	}

	public int commentInsert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sessionTemplate.insert("board.commentInsert", map);
	}

	public String atchFileNo() {
		// TODO Auto-generated method stub
		return sessionTemplate.selectOne("board.atchFileNo");
	}

	public void atchFile(AtchFileVO fileVo) {
		sessionTemplate.insert("board.atchFile", fileVo);
		
	}
	
}
