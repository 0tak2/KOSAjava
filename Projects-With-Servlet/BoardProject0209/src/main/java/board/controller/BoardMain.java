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
import board.vo.Article;

/**
 * Servlet implementation class BoardMain
 */
@WebServlet("/main")
public class BoardMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardMain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 검사
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("loginFail.html");
			return;
		}
		
		List<Article> list = null;
		BoardService service = new BoardService();
		list = service.getAllArticles();
		
		// 게시판 JSP(=Servlet)로 이동 => 서블릿에서 서블릿으로 이동
		// HTML 페이지로 이동하는 것과 다름. RequestDispatcher(JSP_Location) 사용
		RequestDispatcher dispatcher
			= request.getRequestDispatcher("main.jsp");
		
		request.setAttribute("boardList", list); // 세션이 아닌 request에 불러온 게시글을 넣고 JSP에 넘긴다
		
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
