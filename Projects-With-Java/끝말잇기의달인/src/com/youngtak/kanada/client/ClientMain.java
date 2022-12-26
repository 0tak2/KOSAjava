package com.youngtak.kanada.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

enum CMD {
	/* 새로운 닉네임을 지정한다 */ SET_NICKNAME,
	/* 게임을 시작을 요청한다 */ REQUEST_GAME_START,
	/* 연결을 종료한다 */ WILL_DISCONNECT
}

public class ClientMain extends Application {
	private final int PORT = 3333;
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;
	private boolean connected;
	
	private TextArea textArea;
	private Button startBtn;
	private Button stopBtn;
	private Button gameStartBtn;
	private TextField nickNameField;
	private TextField wordField;
	
	private void sendCmd(CMD cmd, String arg) {
		switch(cmd) {
		case SET_NICKNAME:
			pw.println("%SET_NICKNAME" + arg);
			break;
		case REQUEST_GAME_START:
			pw.println("%REQUEST_GAME_START");
			break;
		case WILL_DISCONNECT:
			pw.println("%WILL_DISCONNECT");
		default:
			break;
		}
		pw.flush();
	}
	
	private void sendCmd(CMD cmd) {
		sendCmd(cmd, "");
	}
	
	private void showAlert(String title, String msg) {
		Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(title);
			alert.setHeaderText(title);
			alert.setContentText(msg);
			alert.showAndWait();
		});
	}
	
	public void printMsg(String msg) {
		Platform.runLater(() -> {
			textArea.appendText(msg + "\n");
		});
	}
	
	public void setGameStartButtonText(String text) {
		Platform.runLater(() -> {
			gameStartBtn.setText(text);
		});
	}
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("끝말잇기 클라이언트");
		
		// top
		HBox top = new HBox();
		
		startBtn = new Button("서버 접속");
		startBtn.setPrefSize(190, 40);
		startBtn.setOnAction(e -> {
			try {
				if (nickNameField.getText().equals("")) {
					showAlert("잘못된 닉네임", "닉네임을 다시 입력해주세요.");
					return;
				}
				
				wordField.setDisable(false);
				nickNameField.setDisable(true);
				socket = new Socket("127.0.0.1", PORT);
				pw = new PrintWriter(socket.getOutputStream());
				br = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				
				sendCmd(CMD.SET_NICKNAME, nickNameField.getText());
				
				connected = true;
				gameStartBtn.setDisable(false);
				
				(new Thread(() -> {
					while(true) {
						try {
							if(!connected)
								break;
							
							String received = br.readLine();
							
							if (received.matches("%SERVER_WILL_BE_DOWN")) {
								printMsg("서버와의 연결이 종료됩니다. 서버가 점검 중일 수 있습니다.");
								pw.close();
								br.close();
								socket.close();
								wordField.setDisable(true);
								nickNameField.setDisable(false);
								gameStartBtn.setDisable(true);
								setGameStartButtonText("게임 시작");
								connected = false;
								break;
							} else if (received.contains("%SET_TURN")) {
								String currentPlayer = received.replace("%SET_TURN", "");
								if (currentPlayer.equals(nickNameField.getText())) {
									setGameStartButtonText("현재 차례: 나");
								} else {
									setGameStartButtonText("현재 차례: " + currentPlayer);
								}
								gameStartBtn.setDisable(true);
							} else if (received.contains("%GAME_ENDED")) {
								gameStartBtn.setDisable(false);
								setGameStartButtonText("게임 시작");
							} else {
								printMsg(received);	
							}
						} catch (IOException e1) {
							wordField.setDisable(true);
							nickNameField.setDisable(false);
							gameStartBtn.setDisable(true);
							e1.printStackTrace();
						}	
					}
				})).start();
				
			} catch (IOException e1) {
				showAlert("Error", "서버와의 연결 중 에러가 발생했습니다.\n" + e1.toString());
				wordField.setDisable(true);
				nickNameField.setDisable(false);
				gameStartBtn.setDisable(true);
				setGameStartButtonText("게임 시작");
				connected = false;
				e1.printStackTrace();
			}
		});
		
		stopBtn = new Button("접속 종료");
		stopBtn.setPrefSize(190, 40);
		stopBtn.setOnAction(e -> {
			printMsg("서버와의 접속을 종료합니다.");
			try {
				sendCmd(CMD.WILL_DISCONNECT);
				connected = false;
				pw.close();
				br.close();
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			wordField.setDisable(true);
			nickNameField.setDisable(false);
			gameStartBtn.setDisable(true);
			setGameStartButtonText("게임 시작");
			connected = false;
		});
		
		gameStartBtn = new Button("게임 시작");
		gameStartBtn.setPrefSize(190, 40);
		gameStartBtn.setOnAction(e -> {
			sendCmd(CMD.REQUEST_GAME_START);
		});
		gameStartBtn.setDisable(true);
		
		top.setPadding(new Insets(10));
		top.setSpacing(10);
		top.getChildren().add(startBtn);
		top.getChildren().add(stopBtn);
		top.getChildren().add(gameStartBtn);

		// bottom
		HBox bottom = new HBox();
		
		nickNameField = new TextField();
		nickNameField.setPrefSize(200, 40);
		nickNameField.setPromptText("닉네임");
		
		wordField = new TextField();
		wordField.setPrefSize(460, 40);
		wordField.setPromptText("단어 입력 후 엔터 (기권하려면 '!기권')");
		wordField.setDisable(true);
		wordField.setOnAction(e -> {
			String msg = wordField.getText();
			
			pw.println(msg);
			pw.flush();
			
			wordField.clear();
		});
		
		bottom.setPadding(new Insets(10));
		bottom.setSpacing(10);
		bottom.getChildren().add(nickNameField);
		bottom.getChildren().add(wordField);
		
		
		// center
		textArea = new TextArea();
		
		BorderPane root = new BorderPane();
		root.setTop(top);
		root.setBottom(bottom);
		root.setCenter(textArea);
		
		Scene scene = new Scene(root, 600, 700);
		primaryStage.setScene(scene);
		
		primaryStage.setOnCloseRequest(e -> {
			if (connected) {
				try {
					sendCmd(CMD.WILL_DISCONNECT);
					connected = false;
					pw.close();
					br.close();
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				wordField.setDisable(true);
				nickNameField.setDisable(false);
			}
		});
		
		primaryStage.show();
	}
}
