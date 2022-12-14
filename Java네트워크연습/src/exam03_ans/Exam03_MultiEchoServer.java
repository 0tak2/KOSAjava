package exam03_ans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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

class EchoRunnable implements Runnable {

	Socket socket;
	PrintWriter pr;
	BufferedReader br;
	
	public EchoRunnable() {
		// TODO Auto-generated constructor stub
	}
	
	public EchoRunnable(Socket socket) {
		this.socket = socket;
		
		try {
			pr = new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public void run() {
		while(true) {
			
			String msg = null;
			
			try {
				msg = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(msg.equals("/exit")) {
				break;
			}
			pr.println(msg);
			pr.flush();
		}		
	}
	
}

public class Exam03_MultiEchoServer extends Application {

	TextArea textarea;
	Button startBtn;
	Button stopBtn;
	ServerSocket server;
	Socket socket;
	
	private void printMsg(String msg) {
		Platform.runLater(() -> {
			textarea.appendText(msg + "\n");
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 창의 화면 구성을 하게 되요!
		// Layout
		BorderPane root = new BorderPane();
		root.setPrefSize(700,500); // Layout의 가로세로 크기
		
		textarea = new TextArea();
		root.setCenter(textarea);
		
		startBtn = new Button("서버시작!!");
		startBtn.setPrefSize(150, 40);
		// 버튼을 클릭했을때(클릭은 ActionEvent) 이벤트 처리가 나와요!
		// 이벤트 처리는 Listener객체(Handler객체)가 담당 => delegation model
		// 
		startBtn.setOnAction(e -> {			
			// blocking method!! 실행되는 동안 수행이 잠시 중지되요!
			// 아하 이렇게 하면 안되요!
			// 순차처리를 안하기 위해서 당연히 Thread를 사용해야 되요!	
			printMsg("서버가 시작되었어요!");
			try {
				server = new ServerSocket(5000);
				printMsg("클라이언트 접속대기중!!!");
				
				(new Thread(() -> {
					try {
						while(true) {
							socket = server.accept();
							printMsg("새로운 클라이언트 접속 성공!!");
							(new Thread(new EchoRunnable(socket))).start();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				})).start();				
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
				
		stopBtn = new Button("서버중지!!");
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction(null);
		
		FlowPane flowPane = new FlowPane();
		flowPane.setPadding(new Insets(10,10,10,10));
		flowPane.setColumnHalignment(HPos.CENTER);  // 정렬
		flowPane.setHgap(10);
		
		flowPane.getChildren().add(startBtn);
		flowPane.getChildren().add(stopBtn);
		
		root.setBottom(flowPane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("Echo Server Program");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();   // GUI Thread가 생성되서 우리 창이 실행되요!
	}
	
}
