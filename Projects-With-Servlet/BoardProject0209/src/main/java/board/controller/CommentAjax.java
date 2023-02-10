package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.service.BoardService;
import board.vo.Article;
import board.vo.Comment;
import member.vo.Member;

/**
 * Servlet implementation class CommentAjax
 */
@WebServlet("/comment")
public class CommentAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 검사
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("loginFail.html");
			return;
		}
		
		// 1. 입력
		request.setCharacterEncoding("UTF-8");
		int commentNum = Integer.parseInt(request.getParameter("commentNum"));
		String commentContent = request.getParameter("commentContent");
		
		Member currentUser = (Member) session.getAttribute("member");
		
		// 2. 로직
		boolean success = false;
		BoardService service = new BoardService();
		
		//  현재 로그인 사용자가 게시글 작성자인지 확인 후 수정
		Comment param = new Comment();
		param.setCommentNum(commentNum);
		param.setCommentContent(commentContent);

		Comment comment = service.getOneComment(param);
		if (comment.getCommentAuthor().equals(currentUser.getMemberId())) {
			boolean successDB = service.editComment(param);
			success = successDB;
		} else {
			success = false;
		}
		
		// 3. 출력
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (success) {
			response.setStatus(HttpServletResponse.SC_OK);
			out.println("sucess");
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.println("error");
		}
		
		out.close();
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
