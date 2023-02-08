package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.BookDAO;
import mybatis.MyBatisConnectionFactory;
import vo.BookVO;

/**
 * Servlet implementation class BookSearchServlet
 */
@WebServlet("/bookSearch")
public class BookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearchServlet() {
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
		// 1. 입력 처리
		request.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		
		int price = 0;
		if (request.getParameterValues("price") != null) {
			String priceStr = request.getParameterValues("price")[0];
			price = Integer.parseInt(priceStr);
		}
		
		// 2. 로직 처리
		BookVO target = new BookVO(null, keyword, null, price);
		SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		BookDAO dao = new BookDAO(session);
		List<BookVO> list = dao.select(target);
		session.close();
		
		// 3. 출력 처리
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<table>");
		out.println("<thead><tr><th>ISBN</th><th>제목</th><th>저자</th><th>가격</th></tr></thead>");
		out.println("<tbody>");
		out.println("<h1>조회 결과</h1>");
		out.printf("<span>조건: 키워드=%s, 가격<%d원</span><br>\n", keyword, price);
		out.println("<a href='bookSearch.html'>조회 화면으로 돌아가기</a><br><br>");
		out.printf("<span>총 %d건을 조회하였습니다.</span><br><br>\n", list.size());
		for (BookVO book : list) {
			out.printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
					book.getBisbn(), book.getBtitle(), book.getBauthor(), book.getBprice());
		}
		out.println("</tbody>");
		out.println("</table>");	
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
