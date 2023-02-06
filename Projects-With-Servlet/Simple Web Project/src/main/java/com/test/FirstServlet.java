package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/myservlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FirstServlet() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// 이 서블릿 인스턴스를 초기화
    	System.out.println("init() 호출됨");
    }
    
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    	// TODO Auto-generated method stub
//    	super.service(req, resp);
//    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET http://127.0.0.1:8080/sample/myservlet
		// 어떤 코드를 짜야 할까?
		//    1. 입력을 받는다.   --- 지금은 받을 입력이 없으므로 넘어간다.
		//    2. 로직을 처리한다. --- 처리할 로직이 없으므로 넘어간다.
		//    3. 출력한다.
		
		// 가. MIME 타입 설정
		response.setContentType("text/html; charset=UTF-8");
		
		// 나. 클라이언트에게 데이터를 전달하기 위해 Stream 개방
		PrintWriter out = response.getWriter();
		
		// 다. Stream을 통해 데이터 전달
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>서블릿의 출력 결과. 소리없는 아우성!!</body>");
		out.println("</html>");
		
		// 라. Stream 닫아 자원 할당 해제
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
