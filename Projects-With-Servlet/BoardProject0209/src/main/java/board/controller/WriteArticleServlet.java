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
import board.vo.ArticleExtended;
import common.login.CheckLogin;
import member.vo.Member;

/**
 * Servlet implementation class WriteArticleServlet
 */
@WebServlet("/writeArticle")
public class WriteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 검사
		boolean isLogin = CheckLogin.checkLogin(request, response);
		if (!isLogin) {
			return;
		}
		
		// 1. 입력
		HttpSession session = request.getSession();
		Member currentUser = (Member)session.getAttribute("member");
		String articleAuthor = currentUser.getMemberId();
		
		request.setCharacterEncoding("UTF-8");
		String articleTitle = request.getParameter("articleTitle");
		String articleContent = request.getParameter("articleContent");
		
		// 2. 로직
		BoardService service = new BoardService();
		ArticleExtended newArticle = new ArticleExtended(articleTitle, articleContent, articleAuthor);
		boolean success = service.writeArticle(newArticle);
		
		// 3. 출력
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><script>");
		
		if (success) {
			out.println("alert('성공'); window.location.href='main';");
		} else {
			out.println("alert('실패'); window.location.href='main';");
		}

		out.println("</script></html>");
	}

}
