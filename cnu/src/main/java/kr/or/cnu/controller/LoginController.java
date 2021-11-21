package kr.or.cnu.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.cnu.service.LoginService;
import kr.or.cnu.vo.MemVO;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@GetMapping("/login.do")
	public String loginMain() {
		return "login/login";
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		return "redirect:login.do";
	}
	
	
	
	@PostMapping("/login.do")
	public String loginPost(@RequestParam Map<String, Object>map, Model model, HttpServletRequest req) {
		
		String result = loginService.loginChk(map);
		req.setAttribute("result", result);

		if(result.equals("ok")) {
			
			MemVO memVo = loginService.memInfo((String) map.get("id"));
			
			
			HttpSession session = req.getSession();
			session.setAttribute("memVo", memVo);
			
			
			
			return "redirect:board.do";
		}else{
			return "login/login";
		}
	}
	
	
}
