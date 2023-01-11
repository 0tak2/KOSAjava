package example.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

// FXMLLoader가 객체 생성
// View와 Controller이 1:1 관계가 됨
// 이벤트 처리는 이 클래스 안에서 해야 한다

public class MyController implements Initializable {
	
	@FXML private Button uppderBtn;
	@FXML private Button bottomBtn;
	
	public MyController() {
		System.out.println("호출됨");
	}

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		// 초기화할 내용 (이벤트 리스너 등록 등)
		uppderBtn.setOnAction(e -> {
			// 여기에서 서비스를 생성하여 로직 처리
		});
		
		bottomBtn.setOnAction(e -> {
			// 여기에서 서비스를 생성하여 로직 처리
		});
	}
}
