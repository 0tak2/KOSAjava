package example.view;

import example.controller.Button1Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainView extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Stage 준비 완료. 화면 구성 후 show 필요
		
		// 레이아웃으로 BorderPane 이용
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		Button btn1 = new Button("위쪽 버튼");
		btn1.setOnAction(e -> {
			//이벤트 처리
			Button1Controller controller = new Button1Controller();
			String str = controller.exec();
		});
		
		Button btn2 = new Button("아래쪽 버튼");
		btn2.setOnAction(e -> {
			//이벤트 처리
//			Button2Controller controller = new Button2Controller();
//			String str = controller.exec();
		});
		
		root.setTop(btn1);
		root.setBottom(btn2);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}

}
