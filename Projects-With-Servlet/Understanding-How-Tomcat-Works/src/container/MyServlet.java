package container;

import arguments.HttpServletRequest;
import arguments.HttpServletResponse;

// 서블릿 역할.
// 실제 서블릿 클래스트는 HttpServlet라는 상위 클래스를 상속받아 구현됨.

public class MyServlet {

	public void init() {
		// 초기화
	}
	
	public void service(HttpServletRequest request,
			HttpServletResponse response) {
		// request 객체로부터 요청 메서드를 식별,
		
		// GET이면 doGet() 호출
		doGet(request, response);
		
		// POST면 doPost() 호출
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		// get 요청 처리
		// 입력처리 / 로직처리 / 출력처리
	}
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		// post 요청 처리
		// 입력처리 / 로직처리 / 출력처리
	}
}
