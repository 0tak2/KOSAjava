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
@WebServlet("/ajax/editComment")
public class EditCommentAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCommentAjax() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 검사
		boolean isLogin = CheckLogin.checkLogin(request, response, true);
		if (!isLogin) {
			return;
		}
		
		// 1. 입력
		HttpSession session = request.getSession();
		int commentNum = Integer.parseInt(request.getParameter("commentNum"));
		Member currentUser = (Member)session.getAttribute("member");
		
		// 2. 로직
		boolean isAuthorized = false;
		
		Comment param = new Comment();
		param.setCommentNum(commentNum);
		BoardService service = new BoardService();
		Comment result = service.getOneComment(param);
		
		if (result.getCommentAuthor().equals(currentUser.getMemberId())) {
			isAuthorized = true;
		}
		
		// 3. 출력
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (isAuthorized) {
			response.setStatus(HttpServletResponse.SC_OK);
			out.println(result.getCommentContent());
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.println("unauthorized");
		}
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
		String commentContent = request.getParameter("commentContent");

		Member currentUser = (Member) session.getAttribute("member");
		
		// 2. 로직
		Comment result = null;
		BoardService service = new BoardService();
		
		//  현재 로그인 사용자가 게시글 작성자인지 확인 후 수정
		Comment param = new Comment();
		param.setCommentNum(commentNum);
		param.setCommentContent(commentContent);

		Comment comment = service.getOneComment(param);
		if (comment.getCommentAuthor().equals(currentUser.getMemberId())) {
			Comment successDB = service.editComment(param);
			result = successDB;
		} else {
			result = null;
		}
		
		// 3. 출력
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (result != null) {
			response.setStatus(HttpServletResponse.SC_OK);
			out.println(result.getCommentContent());
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
		// 로그인 검사
		boolean isLogin = CheckLogin.checkLogin(request, response, true);
		if (!isLogin) {
			return;
		}
		
		// 1. 입력
		request.setCharacterEncoding("UTF-8");
		int commentNum = Integer.parseInt(request.getParameter("commentNum"));
		
		System.out.println(commentNum);
		
		// 2, 로직
		
		// 3. 출력
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
//		if (result != null) {
//			response.setStatus(HttpServletResponse.SC_OK);
//			out.println(result.getCommentContent());
//		} else {
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			out.println("error");
//		}
		out.println(commentNum);
		
		out.close();
	}

}
