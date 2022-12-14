package exam03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

import javax.swing.text.PlainDocument;

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

class MyRunnable implements Runnable {
	
	Socket socket;
	PrintWriter pr;
	BufferedReader br;
	
	public MyRunnable() {
	}

	public MyRunnable(Socket socket) {
		super();
		this.socket = socket;
		try {
			this.pr = new PrintWriter(socket.getOutputStream());
			this.br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() { // 실제 클라이언트와 통신하는 부분
		try {
			while (true) {
				// [통신 단계 2번]: 1~2번은 클라이언트
				String msg = br.readLine(); // 버퍼에 데이터가 들어올때까지 일시중단
				
				// [통신 단계 3번]
				pr.println(msg);
				pr.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

public class Exam03_MultiEchoServer extends Application {
	TextArea textArea;
	Button startBtn;
	Button stopBtn;
	
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
			// [이벤트 처리 코드]
			// 모든 처리가 끝날 동안 현재 윈도우가 비활성화됨 (먹통됨)
			// 오래 걸리지 않도록 유의하기. 로딩은 3초를 넘지 않게.
			// 따라서 접속 대기를 새로운 쓰레드를 생성해서 맡긴다.
			
			// 람다를 이용한 축약 STEP 1
			/*
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
			});
			*/
			
			//  STEP 2
			/*
			Thread t = new Thread(() -> { // 위의 코드를 람다로 축약
				
			});
			t.start();
			// 람다로 축약할 수 있는 경우
			// 객체 내의 단 하나의 메서드만 호출되는 경우
			*/
			
			// STEP 3
			(new Thread(() -> { // 위의 코드를 다시 축약. 레퍼런스 없이 쓰기
				// 서버 소켓을 하나 생성 (클라이언트 접속 대기)
				try {
					server = new ServerSocket(7777);
					
					while(true) {
						Socket socket = server.accept(); // 클라이언트의 접속 대기 -> 접속 시 Socket의 객체 반환
						
						 // accept()에 의한 블록이 풀렸다는 것은 새로운 클라이언트가 접속했다는 것
						printMsg("새로운 클라이언트가 접속했습니다.");
						
						// 클라이언트와 직접 통신할 쓰레드를 생성하고 소켓 넘겨줌
						// 소켓을 쓰레드에게 넘겨야하기 때문에 람다로 축약하지 않음
						MyRunnable r = new MyRunnable(socket);
						Thread t = new Thread(r);
						t.start();
					} // 무한 루프로 묶어서 새로운 클라이언트를 다시 기다림
					
				} catch (IOException e1) { // 이미 포트가 점유되었을 수 있으므로 예외처리 강제
					e1.printStackTrace();
				}
			})).start();
			
		});
		
		stopBtn = new Button("서버기동");
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
