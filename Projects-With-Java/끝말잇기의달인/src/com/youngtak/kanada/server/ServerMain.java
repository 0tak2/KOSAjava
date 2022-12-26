package com.youngtak.kanada.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

enum MSG_FROM_SERVER {
	/* 게임을 시작할 수 없습니다. 참가 인원이 부족합니다. */ MEMBER_UNDER_TWO,
	/* 이미 게임이 시작되었습니다 */ ALREADY_STARTED,
	/**/ DEFAULT
}

class SocketRunnable implements Runnable {
	
	private Socket socket;
	private BufferedReader br;
	private Shared shared;
	private String nickname;
	
	public SocketRunnable() {
	}

	public SocketRunnable(Socket socket, Shared shared) {
		super();
		this.socket = socket;
		this.shared = shared;
		try {
			this.br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket getSocket() {
		return this.socket;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	
	public void cmdParser(String cmd) {
		if (cmd.contains("%SET_NICKNAME")) {
			String nickname = cmd.replace("%SET_NICKNAME", "");
			this.nickname = nickname;
			shared.setNickname(this.socket, nickname);
		} else if (cmd.contains("%REQUEST_GAME_START")) {
			MSG_FROM_SERVER result = shared.startGame();
			if (result == MSG_FROM_SERVER.MEMBER_UNDER_TWO) {
				shared.notify(this.socket, "게임을 시작할 수 없습니다. 참가 인원이 부족합니다.");
			} else if (result == MSG_FROM_SERVER.ALREADY_STARTED) {
				shared.notify(this.socket, "게임이 이미 시작되었습니다.");
			} else {
				shared.broadcast("게임을 시작합니다.");
			}
		} else if (cmd.contains("%WILL_DISCONNECT")) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			shared.broadcast(nickname + " 플레이어가 게임을 떠났습니다.");
		}
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				if (socket.isClosed()) {
					br.close();
					shared.deleteSocket(socket);
					return;
				}
				
				if (shared.isShutdownNow()) {
					br.close();
					socket.close();
					break;
				}
				
				String msg = br.readLine();
				if (msg.charAt(0) == '%') {
					cmdParser(msg);
					continue;
				}
				
				if (shared.getGameStatus()) {
					shared.getGM().listen(this.socket, msg);
				} else {
					shared.broadcast(nickname + " > " + msg);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

class Shared {
	private boolean shutdownNow;
	private ServerMain app;
	private ArrayList<Socket> list = new ArrayList<Socket>();
	private HashMap<Socket, PrintWriter> map = new HashMap<Socket, PrintWriter>();
	private HashMap<Socket, String> nickNameMap = new HashMap<Socket, String>();
	private boolean isGameStart;
	GameManager gm;
	
	public boolean isShutdownNow() {
		return shutdownNow;
	}

	public Shared( ) {
	}
	
	public Shared(ServerMain app) {
		super();
		this.app = app;
		isGameStart = false;
		shutdownNow = false;
	}
	
	public synchronized void deleteSocket(Socket socket) {
		list.remove(socket);
		map.remove(socket);
	}
	
	public synchronized GameManager getGM() {
		return gm;
	}
	
	public synchronized MSG_FROM_SERVER startGame() {
		if(list.size() < 2) {
			return MSG_FROM_SERVER.MEMBER_UNDER_TWO;
		}
		if(isGameStart) {
			return MSG_FROM_SERVER.ALREADY_STARTED;
		} else {
			gm = new GameManager(this, list, nickNameMap);
			gm.startTurn();
		}
		
		isGameStart = true;
		return MSG_FROM_SERVER.DEFAULT;
	}
	
	public synchronized boolean getGameStatus() {
		return this.isGameStart;
	}
	
	public synchronized void gameEnded( ) {
		isGameStart = false;
	}
	
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
	
	public synchronized void notify(Socket socket, String msg) {
		String currentNickname = nickNameMap.get(socket);
		map.get(socket).println(msg);
		map.get(socket).flush();
		app.printMsg("[To: " + currentNickname + "] " + msg);
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
	
	public synchronized void setNickname(Socket s, String nickname) {
		nickNameMap.put(s, nickname);
	}
}

public class ServerMain extends Application {
	private final int PORT = 3333;
	private TextArea textArea;
	private Button startBtn;
	private Button stopBtn;
	private Shared shared;
	private ServerSocket server;
	
	public void printMsg(String msg) {
		Platform.runLater(() -> {
			textArea.appendText(msg + "\n");
		});
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("끝말잇기 서버");
		
		// bottom
		HBox bottom = new HBox();
		
		startBtn = new Button("서버 시작");
		startBtn.setPrefSize(190, 40);
		startBtn.setOnAction(e -> {
			shared = new Shared(this);
			
			new Thread(() -> {
				try {
					server = new ServerSocket(PORT);
					printMsg("서버가 시작되었습니다.");
					printMsg("Listening on 0.0.0.0:" + PORT);
					
					while(true) {
						if (shared.isShutdownNow())
							break;
						
						Socket socket = server.accept();
						
						printMsg("새로운 클라이언트가 접속했습니다.");
						
						// 공유 객체에 클라이언트 소켓을 저장
						shared.addClient(socket);
						
						SocketRunnable r = new SocketRunnable(socket, shared); // 러너블 객체에 공유 객체도 넘겨줌
						Thread t = new Thread(r);
						t.start();
					}
					
				} catch (IOException e1) {
					System.err.println("서버 소켓을 생성하고 요청을 대기하던 중 예외가 발생했습니다.");
					System.err.println("이미 서버가 실행되었거나 서버 종료가 시작되었을 수 있습니다.");
					e1.printStackTrace();
				}
			}).start();
		});
		
		stopBtn = new Button("서버 종료");
		stopBtn.setPrefSize(190, 40);
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
		
		Button getMembersBtn = new Button("접속 정보");
		getMembersBtn.setPrefSize(190, 40);
		
		bottom.setPadding(new Insets(10));
		bottom.setSpacing(10);
		bottom.getChildren().add(startBtn);
		bottom.getChildren().add(stopBtn);
		bottom.getChildren().add(getMembersBtn);
		
		// center
		textArea = new TextArea();
		
		BorderPane root = new BorderPane();
		root.setBottom(bottom);
		root.setCenter(textArea);
		
		Scene scene = new Scene(root, 600, 700);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
