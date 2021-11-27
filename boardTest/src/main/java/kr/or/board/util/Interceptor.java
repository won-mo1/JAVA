package kr.or.board.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
	//클라이언트에서 컨트롤러로 요청할 때 가로채는 것.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
//		HttpSession session = request.getSession();
//		String main = String.valueOf(request.getRequestURI());
//		if(!main.equals("/board/main.do")) {
//			if(session.getAttribute("sessionVO") == null) {
//			
//				logger.warn("로그인세션이없음");
//				response.sendRedirect(request.getContextPath() + "/main.do");
//				return false;
//				
//			}
//		}
		
		return super.preHandle(request, response, handler);
	}
	
	//컨트롤러에서 클라이언트로 요천할 때 가로채는 것(컨트롤러 호출되고난 후 실행)
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	//컨트롤러 처리끝나고 화면처리까지 모두끝나면 실행되는 메서드
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}
