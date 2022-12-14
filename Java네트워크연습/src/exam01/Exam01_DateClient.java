package exam01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Exam01_DateClient extends Application {

	// field
	TextArea textarea;
	Button connBtn;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 화면 구성
		// 레이아웃
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		// 컴포넌트
		textarea = new TextArea();
		root.setCenter(textarea); // getChildren() 안하고 지정하는 것에 주의
		
		connBtn = new Button("Date 서버 접속");
		connBtn.setPrefSize(150, 40);
		connBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// 접속 버튼을 클릭하면 수행할 일
				textarea.clear(); // TextArea 내용을 지움
				
				// 서버에 접속 시도 = Socket 객체 생성 시도
				// Socket 객체를 생성하는 것 자체가 접속을 시도하는 것임
				// 접속에 성공하면 객체 생성 성공; 접속 실패시 객체 생성 실패
				try {
					// 서버의 IP와 포트가 필요. 루프백 아이피 사용 (=localhost)
					Socket s = new Socket("127.0.0.1", 3000);
					System.out.println("서버 접속 성공");
					
					// s.getInputStream() 만으로는 텍스트를 다루기 불편하기 때문에
					// InputStreamReader, BufferedStreamReader 스트림과 결합시켜준다.
					BufferedReader br = new BufferedReader(
							new InputStreamReader(s.getInputStream()));
					String msg = br.readLine();
					// ^ 스트림을 통해 데이터가 들어올 때까지 기다린다. 서버가 늦게 데이터를 보낸다고 해도 데이터는 유실되지 않는다.
					// 즉, 읽는 사람을 기준으로 동작한다.
					
					System.out.println(msg);
					
					// 스트림과 소켓을 닫아준다.
					br.close();
					s.close();
					
					System.out.println("서버와의 연결이 종료되었습니다.");
					
					
				} catch (UnknownHostException e) { // Socket 생성 자체가 네트워크를 통한 접속 시도이므로 예외처리 필요
					e.printStackTrace();
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("서버 접속 실패");
					alert.setHeaderText("서버와의 접속에 실패하였습니다.");
					alert.setContentText("다시 한 번 시도해보십시오. 문제가 계속되면 관리자에게 문의하십시오.\n" + e.toString());
					alert.showAndWait();
				} catch (IOException e) {
					e.printStackTrace();
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("서버 접속 실패");
					alert.setHeaderText("서버와의 접속에 실패하였습니다.");
					alert.setContentText("다시 한 번 시도해보십시오. 문제가 계속되면 관리자에게 문의하십시오.\n" + e.toString());
					alert.showAndWait();
				} 
				
			}
		});
		
		FlowPane flowpane = new FlowPane();
		flowpane.setPadding(new Insets(10, 10, 10, 10)); // 보기 좋게 여백 잡기. 인자로 Insets 객체 넣어줌.
		flowpane.setColumnHalignment(HPos.CENTER); // 가운데 정렬 (API 보면서 하면 됨)
		flowpane.setPrefSize(700, 40); // 사이즈 지정
		flowpane.setHgap(10); // 여백
		flowpane.getChildren().add(connBtn); // flowpane에 Button 붙임
		
		root.setBottom(flowpane); // flowpane 레이아웃을 south 영역에 붙여줌
		
		// 씬
		Scene scene = new Scene(root);
		
		// 스테이지
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}

}
