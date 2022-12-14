package exam04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

class Shared {
	
	// 필드
	private ArrayList<Socket> list = new ArrayList<Socket>();
	private HashMap<Socket, PrintWriter> map = new HashMap<Socket, PrintWriter>();
	
	// 메서드
	public synchronized void addClient(Socket socket) {
		// 순차적으로 처리되어야 하므로 동기화를 건다.
		// 동기화를 걸지 않으면 발송 순서가 뒤죽박죽 섞이게 될 수도 있다.
		
		// 서버에 새로운 클라이언트가 접속하면, 서버 소켓으로부터 새로운 소켓이 반환됨
		// 이 소켓을 공유객체의 list에 할당하는 작업을 이 메서드에서 수행함
		list.add(socket);
		try {
			map.put(socket, new PrintWriter(socket.getOutputStream()));
			// 이제부터 클라이언트로 향하는 출력 스트림은 공유객체의 map에 있게 됨
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void broadcast(String msg) {
		// 전달받은 문자열을 PrintWriter를 통해 모든 클라이언트에 내보낸다.
		for(Socket s: list) {
			map.get(s).println(msg);
			map.get(s).flush();
		}
	}
}

class MyRunnable implements Runnable {
	
	Socket socket;
	// PrintWriter pr;
	BufferedReader br;
	Shared shared; // 이제 쓰레드는 소켓도 가지고 있고, 공유객체에도 접근 가능
	
	public MyRunnable() {
	}

	public MyRunnable(Socket socket, Shared shared) {
		super();
		this.socket = socket;
		this.shared = shared;
		try {
			// 이제 출력은 공유객체에서 담당
			// this.pr = new PrintWriter(socket.getOutputStream());
			this.br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				String msg = br.readLine();
				
				// 이제 출력은 공유객체에서 담당
				// pr.println(msg);
				// pr.flush();
				
				// 공유객체를 통해서 모든 클라이언트에게 데이터 출력
				shared.broadcast(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

public class Exam04_ChatServer extends Application {
	TextArea textArea;
	Button startBtn;
	Button stopBtn;
	
	Shared shared; // 공유 객체를 필드로 포함
	ServerSocket server;
	
	private void printMsg(String msg) {
		Platform.runLater(() -> { // run 메서드만을 구현하므로 람드로 축약 가능
			textArea.appendText(msg + "\n");
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		textArea = new TextArea();
		root.setCenter(textArea);
		
		startBtn = new Button("서버기동");
		startBtn.setPrefSize(150, 40);
		startBtn.setOnAction(e -> {
			shared = new Shared(); // 공유 객체 생성
			
			(new Thread(() -> {
				try {
					server = new ServerSocket(7777);
					
					while(true) {
						Socket socket = server.accept();
						
						printMsg("새로운 클라이언트가 접속했습니다.");
						
						// 공유 객체에 클라이언트 소켓을 저장
						shared.addClient(socket);
						
						MyRunnable r = new MyRunnable(socket, shared); // 러너블 객체에 공유 객체도 넘겨줌
						Thread t = new Thread(r);
						t.start();
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			})).start();
			
		});
		
		stopBtn = new Button("서버종료");
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction(e -> {
			
		});
		
		FlowPane flowPane = new FlowPane();
		flowPane.setPadding(new Insets(10, 10, 10, 10));
		flowPane.setColumnHalignment(HPos.CENTER);
		flowPane.setPrefSize(700, 40);
		flowPane.setHgap(10);
		flowPane.getChildren().add(startBtn); // getChildren()에 주의.
		flowPane.getChildren().add(stopBtn);
		root.setBottom(flowPane);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Echo Server Program");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}

}
