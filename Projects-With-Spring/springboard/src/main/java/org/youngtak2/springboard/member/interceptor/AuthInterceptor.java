package org.youngtak2.springboard.member.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.youngtak2.springboard.member.vo.Member;

public class AuthInterceptor implements HandlerInterceptor {

	Logger log = LogManager.getLogger("base");
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Member currentMember = (Member)session.getAttribute("currentMember");
		
		log.debug("세션 로그인 정보:" + currentMember);
		
		if(currentMember == null || currentMember.getMemberId() == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
		}
		return true;
	}
}
