package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.service.BoardService;
import board.vo.Like;
import common.login.CheckLogin;
import member.vo.Member;

/**
 * Servlet implementation class SetLikeServlet
 */
@WebServlet("/ajax/setLike")
public class SetLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 검사
		boolean isLogin = CheckLogin.checkLogin(request, response, true);
		if (!isLogin) {
			return;
		}
		
		// 1. 입력
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		int articleNum = Integer.parseInt(request.getParameter("articleNum"));

		Member currentUser = (Member) session.getAttribute("member");
		
		// 2. 로직
		BoardService service = new BoardService();
		Like param = new Like();
		param.setLikeArticle(articleNum);
		param.setLikeMemberId(currentUser.getMemberId());

		boolean result = service.setLike(param);
		
		// 3. 출력
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (result) {
			response.setStatus(HttpServletResponse.SC_OK);
			out.println("success");
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.println("error");
		}
		
		out.close();
	}

}
