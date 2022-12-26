package com.youngtak.kanada.server;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ServerMain extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("가위바위보 서버");
		
		
		Button startBtn = new Button("서버 시작");
		startBtn.setPrefSize(190, 40);
		
		Button stopBtn = new Button("서버 종료");
		stopBtn.setPrefSize(190, 40);
		
		Button getMembersBtn = new Button("접속 정보");
		getMembersBtn.setPrefSize(190, 40);
		
		HBox bottom = new HBox();
		bottom.setPadding(new Insets(10));
		bottom.setSpacing(10);
		bottom.getChildren().add(startBtn);
		bottom.getChildren().add(stopBtn);
		bottom.getChildren().add(getMembersBtn);
		
		BorderPane root = new BorderPane();
		root.setBottom(bottom);
		
		Scene scene = new Scene(root, 600, 700);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
