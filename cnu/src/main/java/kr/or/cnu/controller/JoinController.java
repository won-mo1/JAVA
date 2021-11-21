package kr.or.cnu.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.cnu.service.JoinService;
import kr.or.cnu.vo.MemVO;

@Controller
public class JoinController {
	
	
	//컨트롤러 >> 서비스 >> 다오 >> 데이터베이스
	@Autowired
	JoinService joinService;
	
	@GetMapping("/join.do")
	public String joinMain() {
		return "join/join";
	}
	
	@PostMapping("/join.do")
	public String joinPost(@ModelAttribute MemVO memVo) {
		
		joinService.memInsert(memVo);
		
		return "login/login";
	}
	
}
