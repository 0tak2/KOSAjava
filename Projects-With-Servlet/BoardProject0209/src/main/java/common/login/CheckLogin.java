package common.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLogin {
	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return checkLogin(request, response, false);
	}
	
	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response, boolean invoke400) throws IOException {
		boolean isLogin = true;
		
		HttpSession session = request.getSession(false);
		
		String url = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		String fullUrl = null;
		if (queryString != null) {
			fullUrl = url + "?" + queryString;			
		} else {
			fullUrl = url;
		}
		
		if (session == null) { // 세션이 없는 경우
			isLogin = false;
		} else if (session.getAttribute("member") == null) { // 세션이 있으나 내부에 Member 객체가 없는 경우
			isLogin = false;
		} else { // 로그인 되어있음
			return true;			
		}
		
		if (!isLogin) {
			if (invoke400) {
				response.setContentType("text/plain; charset=UTF-8");
				PrintWriter out = response.getWriter();
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				out.println("unauthorized");
			}
			response.sendRedirect("login.jsp?nextUrl=" + fullUrl);
		}
		
		return false;
	}
}
