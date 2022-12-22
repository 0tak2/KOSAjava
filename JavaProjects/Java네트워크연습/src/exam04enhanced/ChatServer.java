package exam04enhanced;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

class Shared {
	
	// 필드
	private boolean shutdownNow;
	private ChatServer app;
	private ArrayList<Socket> list = new ArrayList<Socket>();
	private HashMap<Socket, PrintWriter> map = new HashMap<Socket, PrintWriter>();
	
	public boolean isShutdownNow() {
		return shutdownNow;
	}

	public Shared( ) {
	}
	
	public Shared(ChatServer app) {
		super();
		this.app = app;
		shutdownNow = false;
	}
	
	// 메서드
	public synchronized void addClient(Socket socket) {
		list.add(socket);
		try {
			map.put(socket, new PrintWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void broadcast(String msg) {
		for(Socket s: list) {
			map.get(s).println(msg);
			map.get(s).flush();
		}
		app.printMsg(msg);
	}
	
	public synchronized void closeAll() {
		if(this.shutdownNow) {
			app.printMsg("서버가 이미 종료되어있습니다.");
			return;
		}

		broadcast("서버가 10초 후 종료됩니다.");
		broadcast("%SERVER_WILL_BE_DOWN");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(Socket s: list) {
			map.get(s).close();
		}
		app.printMsg("모든 Stream을 닫았습니다.");
		
		this.shutdownNow = true;
		app.printMsg("모든 Thread에게 종료할 것을 요청했습니다.");
	}
}

class MyRunnable implements Runnable {
	
	Socket socket;
	BufferedReader br;
	Shared shared;
	
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
				if(shared.isShutdownNow()) {
					br.close();
					socket.close();
					break;
				}
				
				String msg = br.readLine();
				shared.broadcast(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

public class ChatServer extends Application {
	TextArea textArea;
	Button startBtn;
	Button stopBtn;
	
	Shared shared; // 공유 객체를 필드로 포함
	ServerSocket server;
	Thread guiThread;
	
	public void printMsg(String msg) {
		Platform.runLater(() -> {
			textArea.appendText(msg + "\n");
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		guiThread = Thread.currentThread();
		
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		textArea = new TextArea();
		root.setCenter(textArea);
		
		startBtn = new Button("서버기동");
		startBtn.setPrefSize(150, 40);
		startBtn.setOnAction(e -> {
			shared = new Shared(this); // 공유 객체 생성
			
			(new Thread(() -> {
				try {
					server = new ServerSocket(7777);
					printMsg("서버가 시작되었습니다.");
					
					while(true) {
						if (shared.isShutdownNow())
							break;
						
						Socket socket = server.accept();
						
						printMsg("새로운 클라이언트가 접속했습니다.");
						
						// 공유 객체에 클라이언트 소켓을 저장
						shared.addClient(socket);
						
						MyRunnable r = new MyRunnable(socket, shared); // 러너블 객체에 공유 객체도 넘겨줌
						Thread t = new Thread(r);
						t.start();
					}
					
				} catch (IOException e1) {
					System.err.println("서버 소켓을 생성하고 요청을 대기하던 중 예외가 발생했습니다.");
					System.err.println("이미 서버가 실행되었거나 서버 종료가 시작되었을 수 있습니다.");
					e1.printStackTrace();
				}
			})).start();
			
		});
		
		stopBtn = new Button("서버종료");
		stopBtn.setPrefSize(150, 40);
		stopBtn.setOnAction(e -> {
			if (shared.isShutdownNow()) {
				printMsg("서버가 이미 종료되어있습니다.");
				return;
			}
			new Thread(() -> {
				shared.closeAll();
				try {
					server.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				printMsg("서버 소켓을 닫았습니다.");
			}).start();
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
