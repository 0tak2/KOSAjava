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
import board.vo.Article;
import member.vo.Member;

/**
 * Servlet implementation class DeleteArticleServlet
 */
@WebServlet("/deleteArticle")
public class DeleteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 검사
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("loginFail.html");
			return;
		}
		
		// 1. 입력
		request.setCharacterEncoding("UTF-8");
		int articleNum = Integer.parseInt(request.getParameter("articleNum"));
		
		Member currentUser = (Member) session.getAttribute("member");
		
		// 2. 로직
		boolean success = false;
		BoardService service = new BoardService();
		
		//  현재 로그인 사용자가 게시글 작성자인지 확인 후 삭제
		Article param = new Article();
		param.setArticleNum(articleNum);
		Article article = service.getArticle(param);
		if (article.getArticleAuthor().equals(currentUser.getMemberId())) {
			boolean successDB = service.deleteArticle(param);
			success = successDB;
		} else {
			success = false;
		}
		
		// 3. 출력
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><script>");
		
		if (success) {
			out.println("alert('성공적으로 삭제했습니다.'); window.location.href='main';");
		} else {
			out.println("alert('삭제에 실패했습니다.'); window.location.href='main';");
		}

		out.println("</script></html>");
	}

}
