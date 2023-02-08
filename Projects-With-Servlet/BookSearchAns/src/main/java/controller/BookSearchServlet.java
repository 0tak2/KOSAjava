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

import mybatis.MyBatisConnectionFactory;
import vo.Book;

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
		request.setCharacterEncoding("UTF-8"); // 한글
		
		String keyword = request.getParameter("keyword");
		String price = request.getParameter("price");
		
		// 2. 로직 처리
		Book book = new Book(); // MyBatis에 넘겨줄 VO 객체
		book.setBtitle(keyword);
		book.setBprice(Integer.parseInt(price));
		
		SqlSession session =
				MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		List<Book> result = session.selectList("myBook.selectBookByKeyword", book);
		session.close();
		
		// 3. 출력 처리
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<h1>조회 결과</h1>");
		out.printf("<h3>컴색 키워드: %s</h3>", keyword, price);
		out.printf("<h3>컴색 가격: %s</h3>", price);
		out.println("<ul>");
		for (Book item : result) {
			out.printf("<li>%s, %s, %s, %d</li>",
					item.getBisbn(), item.getBtitle(), item.getBauthor(), item.getBprice());
		}
		out.println("</ul>");
		out.println("</body>");
		out.println("</html>");
		out.close();
		
	}

}
