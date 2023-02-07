package container;

import arguments.HttpServletRequest;
import arguments.HttpServletResponse;
import threadinvoker.ClientThread;

// Tomcat(WAS) 안의 Servlet Container 역할
public class ServletContainer {

	// 실제 Servlet Container에는 없지만, 일반 자바프로그램이므로 넣어줌
	// 클라이언트의 request가 들어온 경우 호출되는 메서드로 생각하기
	public static void main(String[] args) {
		// 1. request 분석. 실행되어야 하는 서블릿을 식별
		
		// 2. 요청된 서블릿의 인스턴스가 컨테이너 내에 있는지를 확인
		
		// 3. 생성된 인스턴스가 없으면 생성
		MyServlet servlet = new MyServlet();
		
		// 4. 인스턴트 생성 후 초기화
		servlet.init();
		
		// 5. 클라이언트의 request 내용을 분석하여 request 객체 생성
		HttpServletRequest request = new HttpServletRequest();
		
		// 6. 클라이언트에 대해 response를 보내주기 위한 response 객체 생성
		HttpServletResponse response = new HttpServletResponse();
		
		// 7. 클라이언트의 요청을 실제로 처리하는 쓰레드 생성
		ClientThread t = new ClientThread(servlet, request, response);
		t.start(); // Tomcat에서는 ThreadInvoker가 담당
		
		
	}
}
