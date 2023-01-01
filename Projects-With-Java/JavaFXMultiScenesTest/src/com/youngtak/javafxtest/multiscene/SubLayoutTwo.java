package com.youngtak.javafxtest.multiscene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SubLayoutTwo {	
	@FXML
	private Button btn;
	
	public SubLayoutTwo() {
	}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleBtn() {
		System.out.println("Clicked");
	}
}
