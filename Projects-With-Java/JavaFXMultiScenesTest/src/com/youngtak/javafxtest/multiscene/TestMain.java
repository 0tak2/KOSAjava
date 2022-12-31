package com.youngtak.javafxtest.multiscene;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TestMain extends Application {
	private Stage stage;
	Scene mainScene;
	
	private Scene getMainScene() {
		return mainScene;
	}
	
	private void changeScene(Scene scene) {
		stage.setScene(scene);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		TextArea textArea = new TextArea();
		textArea.setText("씬 1입니다");
		
		HBox bottom = new HBox();
		Button scene1Btn = new Button("씬 1");
		Button scene2Btn = new Button("씬 2");
		Button scene3Btn = new Button("씬 3");
		scene1Btn.setPrefSize(190, 40);
		scene1Btn.setOnAction(e -> {
			changeScene(mainScene);
		});
		scene2Btn.setPrefSize(190, 40);
		scene2Btn.setOnAction(e -> {
			changeScene(new Scene2Factory().getScene());
		});
		scene3Btn.setPrefSize(190, 40);
		scene3Btn.setOnAction(e -> {
			changeScene(new Scene3Factory().getScene());
		});
		bottom.getChildren().add(scene1Btn);
		bottom.getChildren().add(scene2Btn);
		bottom.getChildren().add(scene3Btn);
		bottom.setPadding(new Insets(10));
		
		BorderPane root = new BorderPane();
		root.setBottom(bottom);
		root.setCenter(textArea);
		
		
		mainScene = new Scene(root, 700, 400);
		
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
