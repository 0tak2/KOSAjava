package com.youngtak.javafxtest.multiscene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SubLayoutThree {	
	@FXML
	private Button btn;
	
	public SubLayoutThree() {
	}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleBtn() {
		System.out.println("Clicked");
	}
}
