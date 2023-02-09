package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import mybatis.MyBatisConnectionFactory;
import vo.Account;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String inputID = request.getParameter("userID");
		String inputPW = request.getParameter("userPW");
		
		// 2. 로직 처리
		Account target = new Account();
		target.setId(inputID);
		target.setPassword(inputPW);
		
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		Account result = session.selectOne("account.selectByIdAndPw", target);
		
		// 3. 출력 처리
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head></head>");
		out.println("<body>");
		
		if(result != null) {
			out.printf("%s(%s)님 다시 오신 것을 환영합니다.<br>\n", result.getName(), result.getId());
		} else {
			out.println("<script>");
			out.println("alert('로그인에 실패했습니다. 아이디와 비밀번호를 다시 확인해주세요.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		out.println("</body></html>");
		out.close();
	}

}
