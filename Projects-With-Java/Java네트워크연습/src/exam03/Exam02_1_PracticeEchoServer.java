package exam03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

class SharedObj {
	Exam02_1_PracticeEchoServer app;
	ArrayList<Socket> ss;
	TextArea textarea;
	
	SharedObj() {
	}
	
	SharedObj(Exam02_1_PracticeEchoServer app) {
		super();
		this.ss = new ArrayList<>();
		this.app = app;
	}
	
	public void printMsg(String msg) {
		app.printMsg(msg);
	}
	
	public void addSocket(Socket s) {
		ss.add(s);
	}
	
	public synchronized void echoEverybody(String msg) {
		Iterator<Socket> it = ss.iterator();
		
		while (it.hasNext()) {
			Socket s = it.next();
			PrintWriter pr;
			try {
				pr = new PrintWriter(s.getOutputStream());
				pr.println(msg);
				pr.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void closeEverybody() {
		Iterator<Socket> it = ss.iterator();
		
		while (it.hasNext()) {
			Socket s = it.next();
			try {
				s.close();
				printMsg("서버를 종료했습니다.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class EchoTread implements Runnable {
	Socket socket;
	SharedObj shared;
	PrintWriter pr;
	BufferedReader br;

	public EchoTread(Socket socket, SharedObj shared) {
		super();
		this.socket = socket;
		this.shared = shared;
		shared.addSocket(socket);
	}

	@Override
	public void run() {
		try {
			shared.printMsg("클라이언트 접속 성공!!");
			pr = new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while(true) {
				String msg = br.readLine();
				shared.printMsg("클라이언트의 메시지 : " + msg);
				
				if(msg.equals("/exit")) {
					break;
				}
				shared.echoEverybody(msg);
			}
			
			shared.printMsg("클라이언트 종료! & 서버 Process 종료");
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}

public class Exam02_1_PracticeEchoServer extends Application {

	SharedObj shared;
	
	TextArea textarea;
	Button startBtn;
	Button stopBtn;
	ServerSocket server;
	
	public void printMsg(String msg) {
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
				
				shared = new SharedObj(this);
				
				(new Thread(() -> {
					while (true) {
						Socket s;
						try {
							s = server.accept();
							if (s != null) {
								(new Thread(new EchoTread(s, shared))).start();
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				})).start();
				
				/*
				(new Thread(() -> {
					try {
						s = server.accept();
						printMsg("클라이언트 접속 성공!!");
						pr = new PrintWriter(s.getOutputStream());
						br = new BufferedReader(new InputStreamReader(s.getInputStream()));
						
						while(true) {
							String msg = br.readLine();
							printMsg("클라이언트의 메시지 : " + msg);
							
							if(msg.equals("/exit")) {
								break;
							}
							pr.println(msg);
							pr.flush();
						}
						
						printMsg("클라이언트 종료! & 서버 Process 종료");
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				})).start();		*/		
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
				
		stopBtn = new Button("서버중지!!");
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction(e -> {
			shared.closeEverybody();
			try {
				server.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
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
