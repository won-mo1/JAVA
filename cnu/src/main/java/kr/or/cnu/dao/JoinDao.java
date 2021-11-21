package kr.or.cnu.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.cnu.vo.MemVO;

@Repository
public class JoinDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void memInsert(MemVO memVo) {
		
		sqlSessionTemplate.insert("join.join", memVo);
	} 
	
	
	
}
