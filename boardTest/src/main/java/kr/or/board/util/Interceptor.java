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
	
	//Ŭ���̾�Ʈ���� ��Ʈ�ѷ��� ��û�� �� ����ä�� ��.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
//		HttpSession session = request.getSession();
//		String main = String.valueOf(request.getRequestURI());
//		if(!main.equals("/board/main.do")) {
//			if(session.getAttribute("sessionVO") == null) {
//			
//				logger.warn("�α��μ����̾���");
//				response.sendRedirect(request.getContextPath() + "/main.do");
//				return false;
//				
//			}
//		}
		
		return super.preHandle(request, response, handler);
	}
	
	//��Ʈ�ѷ����� Ŭ���̾�Ʈ�� ��õ�� �� ����ä�� ��(��Ʈ�ѷ� ȣ��ǰ� �� ����)
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	//��Ʈ�ѷ� ó�������� ȭ��ó������ ��γ����� ����Ǵ� �޼���
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}
