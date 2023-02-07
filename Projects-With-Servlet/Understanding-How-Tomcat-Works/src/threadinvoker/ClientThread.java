package threadinvoker;

import arguments.HttpServletRequest;
import arguments.HttpServletResponse;
import container.MyServlet;

public class ClientThread extends Thread {

	private MyServlet servlet;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public ClientThread(MyServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super();
		this.servlet = servlet;
		this.request = request;
		this.response = response;
	}

	@Override
	public void run() {
		servlet.service(request, response);
	}
}
