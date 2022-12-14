package javafxexam;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Button btn = new Button();
		btn.setText("안녕");
		
		// 버튼에 Action이라는 이벤트를 처리할 수 있는 Listener 객체를 붙임
		// 인자로 Listener 객체를 넘겨줌
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			// EventHandler 인터페이스의 추상메서드를 오버라이딩
			@Override
			// 버튼을 누르면 Listener 객체의 이 메서드가 호출됨
			public void handle(ActionEvent event) { // JVM이 이벤트를 감지하면 Event 클래스를 넘겨줌
				System.out.println("안녕하세요");
			}
		});
		
		StackPane root = new StackPane();
		root.getChildren().add(btn);
		
		Scene scene = new Scene(root, 300, 150);
		
		primaryStage.setTitle("연습중");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
}
