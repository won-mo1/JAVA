package kr.or.cnu.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.cnu.vo.MemVO;

@Repository
public class LoginDao {

	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	public Map<String, Object> memInfo(Object id){
		
		return sessionTemplate.selectOne("join.memInfo", (String) id);
	}

	public MemVO mem(String id) {
		// TODO Auto-generated method stub
		return sessionTemplate.selectOne("join.mem" , id);
	}
	
}
