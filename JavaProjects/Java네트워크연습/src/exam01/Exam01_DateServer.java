package exam01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

public class Exam01_DateServer {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(3000);
			System.out.println("서버 소켓이 생성되었습니다: 포트번호 3000");
			
			// 이 서버 소켓을 통해 클라이언트의 접속을 기다려야 함
			Socket s = server.accept(); // 클라이언트가 접속할 때까지 기다린다
			System.out.println("클라이언트의 접속이 들어왔습니다.");
			
			// 클라이언트와 연결된 Output Stream 생성
			// s.getOutputStream() 만으로는 스트링을 취급하기 불편하기 때문에
			// 여기에 PrintWriter 스트림을 결합하여 사용하기 편하게 함.
			PrintWriter out = new PrintWriter(s.getOutputStream());
			
			// 현재 시간을 보내준다
			// new로 인스턴스화하지 않고, 스태틱 메서드인 getInstance를 이용하여 객체 반환받음 (원래 이렇게 씀)
			DateFormat dateFormat = DateFormat.getInstance();
			String currentDate = dateFormat.format(new Date()); // 외울 필요는 없지만, 많이 사용되는 예시라서 알아두면 좋음.
			
			out.println(currentDate); // 그럼 버퍼에 텍스트가 실려있게 된다.
			out.flush(); // 버퍼를 내보냄. 실려있는 데이터를 클라이언트로 내보냄.
			
			// 서버가 작업을 끝냈으면, 사용한 리소스를 무조건 해제해줘야 함.
			// 효율적인 메모리 사용을 위해 + 클라이언트 측 문제 방지
			out.close(); // 스트림을 닫는다.
			s.close(); // 소켓을 닫는다.
			server.close(); // 서버소켓을 닫는다.
			
			System.out.println("서버가 종료되었습니다.");
			
		} catch (IOException e) { // 지정한 포트가 이미 점유되어 있는 경우 IOException
			e.printStackTrace();
		}
	}
}
