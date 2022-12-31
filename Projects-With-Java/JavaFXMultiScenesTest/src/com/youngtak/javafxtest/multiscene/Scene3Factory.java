package com.youngtak.javafxtest.multiscene;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Scene3Factory {
	public Scene getScene() {
		TextArea textArea = new TextArea();
		textArea.setText("씬 3입니다");
		
		HBox bottom = new HBox();
		Button scene1Btn = new Button("씬 1");
		Button scene2Btn = new Button("씬 2");
		Button scene3Btn = new Button("씬 3");
		scene1Btn.setPrefSize(190, 40);
		scene2Btn.setPrefSize(190, 40);
		scene3Btn.setPrefSize(190, 40);
		bottom.getChildren().add(scene1Btn);
		bottom.getChildren().add(scene2Btn);
		bottom.getChildren().add(scene3Btn);
		bottom.setPadding(new Insets(10));
		
		BorderPane root = new BorderPane();
		root.setBottom(bottom);
		root.setCenter(textArea);
		
		
		Scene scene = new Scene(root, 700, 400);
		
		return scene;
	}
}
