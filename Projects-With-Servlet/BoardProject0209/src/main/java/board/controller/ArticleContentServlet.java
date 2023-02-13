package board.controller;

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
import board.vo.ArticleExtended;
import board.vo.Comment;
import board.vo.Like;
import common.login.CheckLogin;
import member.vo.Member;

/**
 * Servlet implementation class ArticleContentServlet
 */
@WebServlet("/viewArticle")
public class ArticleContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleContentServlet() {
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
		HttpSession session = request.getSession();
		Member currentUser = (Member)session.getAttribute("member");
		
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		
		// 2. 로직
		BoardService service = new BoardService();
		ArticleExtended articleParam = new ArticleExtended();
		articleParam.setArticleNum(articleId);
		ArticleExtended result = service.getArticle(articleParam);
		
		List<Comment> commentsList = service.getAllComments(result);
		
		Like likeParam = new Like();
		likeParam.setLikeArticle(articleId);
		likeParam.setLikeMemberId(currentUser.getMemberId());
		
		boolean didLike = false;
		Like like = service.getLike(likeParam);
		if (like != null) {
			didLike = true;
		} else {
			didLike = false;
		}
		
		// 3. 뷰
		RequestDispatcher dispatcher
			= request.getRequestDispatcher("viewArticle.jsp");
		
		request.setAttribute("article", result);
		request.setAttribute("comments", commentsList);
		request.setAttribute("didLike", didLike);
		
		dispatcher.forward(request, response); // 해당 JSP에 현재 Request와 Response 객체를 넘기며 제어권을 넘긴다.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
