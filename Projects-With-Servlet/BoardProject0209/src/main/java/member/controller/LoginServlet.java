package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.service.BoardService;
import board.vo.Article;
import member.service.MemberService;
import member.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. [입력] 뷰로부터 입력을 받음
		request.setCharacterEncoding("UTF-8");
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		String nextUrl = request.getParameter("nextUrl");
		
		// 2. [로직] 서비스로 VO 넘김
		Member member = new Member();
		member.setMemberId(userID);
		member.setMemberPw(userPW);
		
		MemberService service = new MemberService();
		// 나중에 스프링에 들어가면 IoC를 도입하여 이런 코드는 쓰지 않는다.
		// 100명이 접속한다면 100개의 인스턴스를 생성하게 된다.
		// 스프링이 싱글톤으로 만들어 주입해줄 것이다.
		
		Member result = service.login(member);
		// Boolean으로 받을 수도 있지만, VO로 주고 받는 것이 일반적으로 가장 좋다.
		// 로그인에 성공하면, 결과에서 회원 이름까지 VO에 포함해 들고온다.
		// 로그인에 실패하면 null 리턴	
		
		// 3. [출력] 뷰로 넘김
		if (result != null) {
			// 로그인 성공
			// 1. 로그인 세션 생성
			//    -- 서비스는 '비즈니스 로직'을 처리하는 '일반 자바 클래스'이므로.
			//    -- 세션 처리는 서블릿에서 한다.
			// 2. 게시판 페이지 전송 (JSP)
			//    -- 동적 페이지이므로 JSP를 보내준다.
			
			HttpSession session = request.getSession(true);
			session.setAttribute("member", result); // VO 자체를 저장
			
			if (nextUrl != null) {
				response.sendRedirect(nextUrl);
			} else {
				response.sendRedirect("main");
			}
			
		} else {
			// 로그인 실패
			// 오류 페이지 전송 (HTML)
			//    -- 정적 페이지이므로 HTML을 보내준다.
			
			response.sendRedirect("loginFail.html"); // 리다이렉트 = 다시 접속하라고 응답함
		}
	}

}
