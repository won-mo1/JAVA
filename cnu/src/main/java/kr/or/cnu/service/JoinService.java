package kr.or.cnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.cnu.dao.JoinDao;
import kr.or.cnu.vo.MemVO;

@Service
public class JoinService {

	@Autowired
	JoinDao joinDao;
	
	public void memInsert(MemVO memVo) {
		joinDao.memInsert(memVo);
	}

	
	
}
