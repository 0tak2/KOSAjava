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
import board.vo.Comment;
import member.vo.Member;

/**
 * Servlet implementation class WriteCommentServlet
 */
@WebServlet("/writeComment")
public class WriteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteCommentServlet() {
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
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("loginFail.html");
			return;
		}
		
		// 1. 입력
		request.setCharacterEncoding("UTF-8");
		int articleNum = Integer.parseInt(request.getParameter("articleNum"));
		
		Member currentUser = (Member) session.getAttribute("member");
		String commentAuthor = currentUser.getMemberId();
		
		String commentContent = request.getParameter("commentContent");
		
		// 2. 로직
		BoardService service = new BoardService();
		Comment newComment = new Comment(commentContent, commentAuthor, articleNum);
		boolean success = service.writeComment(newComment);
		
		// 3. 출력
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><script>");
		
		if (success) {
			out.printf("window.location.href='viewArticle?articleId=' + %d;", articleNum);
		} else {
			out.printf("alert('실패'); window.location.href='viewArticle?articleId=' + %d;", articleNum);
		}

		out.println("</script></html>");
	}

}
