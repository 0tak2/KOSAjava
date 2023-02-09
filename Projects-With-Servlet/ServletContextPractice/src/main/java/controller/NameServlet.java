package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NameServlet
 */
@WebServlet("/saveName")
public class NameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NameServlet() {
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
		String userName = request.getParameter("userName");
		
		// 2. 로직 처리
		ServletContext context = this.getServletContext(); // ServletContext 가져오기
		context.setAttribute("memberName", userName); // Key, Value의 쌍으로 데이터 저장
		
		// 3. 출력 처리
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head></head>");
		out.println("<body>");
		out.printf("You Input: %s<br>\n", userName);
		out.println("<a href='secondServlet'>두번째 서블릿 호출</a>");
		out.println("</body></html>");
		out.close();
	}

}
