package exam03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Exam03_MultiEchoClient extends Application {

	TextArea textArea;
	TextField ipTextField;
	Button connBtn;
	TextField nameTextField;
	TextField chatTextField;
	
	Socket socket;
	PrintWriter pr;
	BufferedReader br;
	
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
		
		
		FlowPane topFlowPane = new FlowPane();
		topFlowPane.setPadding(new Insets(10, 10, 10, 10));
		topFlowPane.setColumnHalignment(HPos.CENTER);
		topFlowPane.setPrefSize(700, 40);
		topFlowPane.setHgap(10);
		
		ipTextField = new TextField();
		ipTextField.setPrefSize(450, 40);
		connBtn =  new Button("서버 접속");
		
		connBtn.setPrefSize(200, 40);
		connBtn.setOnAction(e -> {
			// 서버에 접속 = 특정 아이피, 포트에 대해 소켓 객체 생성 시도
			// 접속에 성공하면 서버와 연결된 소켓 객체를 반환
			try {
				socket = new Socket(ipTextField.getText(), 7777);
				pr = new PrintWriter(socket.getOutputStream());
				br = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
		
		topFlowPane.getChildren().add(ipTextField);
		topFlowPane.getChildren().add(connBtn);
		
		
		FlowPane bottomFlowPane = new FlowPane();
		bottomFlowPane.setPadding(new Insets(10, 10, 10, 10));
		bottomFlowPane.setColumnHalignment(HPos.CENTER);
		bottomFlowPane.setPrefSize(700, 40);
		bottomFlowPane.setHgap(10);
		
		nameTextField = new TextField();
		nameTextField.setPrefSize(150, 40);
		
		chatTextField = new TextField();
		chatTextField.setPrefSize(500, 40);
		chatTextField.setOnAction(e -> {
			// 엔터 입력
			String name = nameTextField.getText();
			String msg = chatTextField.getText();
			
			// [통신 단계 1번]: 2~3번은 서버
			pr.println(name + "> " + msg);
			pr.flush();
			
			// [통신 단계 4번]
			try {
				String received = br.readLine();
				printMsg(received);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		bottomFlowPane.getChildren().add(nameTextField);
		bottomFlowPane.getChildren().add(chatTextField);
		
		
		root.setTop(topFlowPane);
		root.setBottom(bottomFlowPane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Echo Client Program");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}

}
