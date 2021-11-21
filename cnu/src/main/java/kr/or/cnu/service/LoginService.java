package kr.or.cnu.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.cnu.dao.LoginDao;
import kr.or.cnu.vo.MemVO;

@Service
public class LoginService {
	
	@Autowired
	LoginDao loginDao;
	
	public String loginChk(Map<String, Object> map) {
		
		Map<String, Object> memInfo = loginDao.memInfo(map.get("id"));
		System.out.println(memInfo);
		
		if(memInfo != null) {
			if(map.get("pwd").equals(memInfo.get("PWD"))) {
				return "ok";
			}else {
				return "pwdFail";
			}
		}else {
			return "idFail";
		}
		
	}

	public MemVO memInfo(String id) {
		return loginDao.mem(id);
	}

	
	
}
