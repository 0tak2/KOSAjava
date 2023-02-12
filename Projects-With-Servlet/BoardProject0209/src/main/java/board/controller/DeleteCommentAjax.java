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
import common.login.CheckLogin;
import member.vo.Member;

/**
 * Servlet implementation class CommentAjax
 */
@WebServlet("/ajax/deleteComment")
public class DeleteCommentAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCommentAjax() {
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
		int commentNum = Integer.parseInt(request.getParameter("commentNum"));

		Member currentUser = (Member) session.getAttribute("member");
		
		// 2. 로직
		boolean result = false;
		BoardService service = new BoardService();
		
		//  현재 로그인 사용자가 게시글 작성자인지 확인 후 수정
		Comment param = new Comment();
		param.setCommentNum(commentNum);

		Comment comment = service.getOneComment(param);
		if (comment.getCommentAuthor().equals(currentUser.getMemberId())) {
			boolean successDB = service.deleteComment(param);
			result = successDB;
		} else {
			result = false;
		}
		
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
		out.println(commentNum);
		
		out.close();
	}

}
