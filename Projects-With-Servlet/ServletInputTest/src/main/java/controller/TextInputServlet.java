package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TextInputServlet
 */
@WebServlet("/inputTest")
public class TextInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 필드 (Stateful)
	int count = 0; // 클라이언트 스레드에 의해 공유됨
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TextInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		count += 1; // Stateful. 정말 공유하는가?
		
		// MVC를 배우기 전까지는 여기서 모두 수행
		// 1. 입력 처리
//		request.setCharacterEncoding("UTF-8");
		String userInput = request.getParameter("userID");
		
		// 2. 로직 처리 - 없음
		
		// 3. 출력 처리
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head></head>");
		out.printf("<body>[GET] %s님 환영합니다.</body>", userInput);
		out.println("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		count += 1; // Stateful. 정말 공유하는가?
		
		// MVC를 배우기 전까지는 여기서 모두 수행
		// 1. 입력 처리
		request.setCharacterEncoding("UTF-8");
		String userInput = request.getParameter("userID");
		
		// 2. 로직 처리 - 없음
		
		// 3. 출력 처리
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head></head>");
		out.printf("<body>[POST] %s님 환영합니다.</body>", userInput);
		out.println("</html>");
		out.close();
	}

}
