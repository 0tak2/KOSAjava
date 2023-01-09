package com.youngtak.javafxtest.multiscene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SubLayoutOne {	
	@FXML
	private Button btn;
	
	public SubLayoutOne() {
	}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleBtn() {
		System.out.println("Clicked");
	}
}
