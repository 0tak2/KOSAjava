package com.youngtak.javafxtest.multiscene;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestMain extends Application {
	@FXML
	private MenuItem menuitemLayoutOne;
	private MenuItem menuitemLayoutTwo;
	private MenuItem menuitemLayoutThree;
	
	private VBox root;
	private AnchorPane subLayoutOne;
	private AnchorPane subLayoutTwo;
	private AnchorPane subLayoutThree;
	
	@FXML
	private void handleMenuitemLayoutOne() {
		System.out.println("change to 1");
		changeSubLayout((Node)subLayoutOne);
	}
	
	@FXML
	private void handleMenuitemLayoutTwo() {
		System.out.println("change to 2");
		changeSubLayout((Node)subLayoutTwo);
	}
	
	@FXML
	private void handleMenuitemLayoutThree() {
		System.out.println("change to 3");
		changeSubLayout((Node)subLayoutThree);
	}
	
	@FXML
	private void initialize( ) {
	}
	
	private void changeSubLayout(Node n) {
		root.getChildren().set(1, n);
	}
	
	@Override
	public void init() throws Exception {
		try {
			root = FXMLLoader.load(getClass().getResource("MainLayoutFXML.fxml"));
			subLayoutOne = FXMLLoader.load(getClass().getResource("SubLayoutOneFXML.fxml"));
			subLayoutTwo = FXMLLoader.load(getClass().getResource("SubLayoutTwoFXML.fxml"));
			subLayoutThree = FXMLLoader.load(getClass().getResource("SubLayoutThreeFXML.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root.getChildren().add(subLayoutOne);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
