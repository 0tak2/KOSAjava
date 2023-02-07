package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWroldServlet
 */
@WebServlet("/sayhello")
public class HelloWroldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWroldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 입력 처리: response 객체 이용
		// 2. 로직 처리 / DB처리 포함
		// 3. 응답 처리: request 객체 이용
		// => MVC 패턴을 배우면 일련의 작업을 각각 다른 부분에서 수행하도록 분할할 수 있다.
		
		// 처리 결과를 전송하기 전에, 보낼 데이터의 MIME 타입을 먼저 전송해야 함
		// MIME을 잘못 보내면 브라우저가 렌더링하지 못하고 다운로드 프롬프트 표시
		response.setContentType("text/html; charset=UTF-8"); // 대분류/소분류
		
		// 결과 데이터 전송
		PrintWriter out = response.getWriter();
		
		out.println("<html><head></head><body>Hello, World!</body></html>");
		// HTML 문서를 통 문자열로 보내고 있다. 되기는 되지만 불편하다.
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
