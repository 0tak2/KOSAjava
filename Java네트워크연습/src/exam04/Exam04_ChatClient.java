package exam04;

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

public class Exam04_ChatClient extends Application {

	TextArea textArea;
	TextField ipTextField;
	Button connBtn;
	TextField nameTextField;
	TextField chatTextField;
	
	Socket socket;
	PrintWriter pr;
	BufferedReader br;
	
	private void printMsg(String msg) {
		Platform.runLater(() -> {
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
			try {
				socket = new Socket(ipTextField.getText(), 7777);
				pr = new PrintWriter(socket.getOutputStream());
				br = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				
				(new Thread(() -> { // 람다로 러너블 객체를 넣는다
					while(true) {
						try {
							String received = br.readLine();
							printMsg(received);
						} catch (IOException e1) {
							e1.printStackTrace();
						}	
					}
				})).start(); // 데이터가 들어올 때마다 printMsg() 호출됨
				
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
			String name = nameTextField.getText();
			String msg = chatTextField.getText();
			
			pr.println(name + "> " + msg);
			pr.flush();
			
			chatTextField.clear();
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
