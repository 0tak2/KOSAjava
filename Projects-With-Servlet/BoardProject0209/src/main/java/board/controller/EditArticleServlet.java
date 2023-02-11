package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.service.BoardService;
import board.vo.Article;
import common.login.CheckLogin;
import member.vo.Member;

/**
 * Servlet implementation class EditArticleServlet
 */
@WebServlet("/editArticle")
public class EditArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 검사
		boolean isLogin = CheckLogin.checkLogin(request, response);
		if (!isLogin) {
			return;
		}
		
		// 1. 입력
		int articleNum = Integer.parseInt(request.getParameter("articleNum"));
		
		// 2. 로직
		BoardService service = new BoardService();
		Article param = new Article();
		param.setArticleNum(articleNum);
		Article result = service.getArticle(param);
		
		// 3. 출력
		RequestDispatcher dispatcher
			= request.getRequestDispatcher("editArticle.jsp");
		
		request.setAttribute("article", result);
		
		dispatcher.forward(request, response);
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
		request.setCharacterEncoding("UTF-8");
		int articleNum = Integer.parseInt(request.getParameter("articleNum"));
		String articleTitle  = request.getParameter("articleTitle");
		String articleContnet = request.getParameter("articleContent");
		
		Member currentUser = (Member) session.getAttribute("member");
		
		// 2. 로직
		boolean success = false;
		BoardService service = new BoardService();
		
		//  현재 로그인 사용자가 게시글 작성자인지 확인 후 수정
		Article param = new Article();
		param.setArticleNum(articleNum);
		param.setArticleTitle(articleTitle);
		param.setArticleContent(articleContnet);
		Article article = service.getArticle(param);
		if (article.getArticleAuthor().equals(currentUser.getMemberId())) {
			boolean successDB = service.editArticle(param);
			success = successDB;
		} else {
			success = false;
		}
		
		// 3. 출력
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><script>");
		
		if (success) {
			out.println("alert('성공적으로 수정했습니다.'); window.location.href='main';");
		} else {
			out.println("alert('수정에 실패했습니다.'); window.location.href='main';");
		}

		out.println("</script></html>");
		out.close();
	}

}
