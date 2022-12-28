package lecture.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lecture.jdbc.vo.BookVO;

public class BookSearchJavaFX extends Application {
	private TableView<BookVO> tableView; // VO 클래스를 제네릭으로 지정. 한 로우가 하나의 VO에 대응.
	private TextField textField;
	private String jdbcUrl;
	private String id;
	private String pw = "test1234";
	private ObservableList<BookVO> list;
	
	@Override
	public void init() throws Exception {
		super.init();
		
		// 드라이버 로드 및 DB 접속 정보 할당
		Class.forName("com.mysql.cj.jdbc.Driver");
		jdbcUrl = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
		id = "root";
		pw = "test1234";
		
		// TableView에 들어갈 데이터 만들기
		// ArrayList 자체를 쓰지않고, 이와 유사한 List를 사용
		list = FXCollections.observableArrayList();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 1. 루트 레이아웃 잡기
		// BorderPane 이용 (동 서 남 북 중앙)
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		// 2. 루트 레아아웃 아래 붙일 FlowPane
		FlowPane flowpane = new FlowPane();
		flowpane.setPadding(new Insets(10, 10, 10, 10));
		flowpane.setColumnHalignment(HPos.CENTER);
		flowpane.setPrefSize(700, 40);
		flowpane.setHgap(10);
		
		// 3. 각 컴포넌트를 생성하여 Pane에 위치
		textField = new TextField();
		textField.setPrefSize(250, 40);
		textField.setOnAction(e -> {
			try {
				list.clear();
				
				Connection con = DriverManager.getConnection(jdbcUrl, id, pw);
				StringBuffer sqlBuf = new StringBuffer();
				sqlBuf.append("SELECT BISBN, BTITLE, BDATE, BAUTHOR, BPRICE ");
				sqlBuf.append("FROM BOOK ");
				sqlBuf.append("WHERE BTITLE LIKE ? ");
				sqlBuf.append("ORDER BY BPRICE DESC");
				
				String sql = sqlBuf.toString();
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				String keyword = textField.getText();
				pstmt.setString(1, "%" + keyword + "%");
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BookVO book = new BookVO(rs.getString("bisbn"),
												rs.getString("btitle"),
												rs.getString("bauthor"),
												rs.getInt("bprice"));
					list.add(book);
				}
				
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		flowpane.getChildren().add(textField);
		
		// 컬럼 객체를 생성
		// 제네릭: <데이터 소스가 되는 VO의 클래스, 컬럼에 들어가는 값의 타입>
		TableColumn<BookVO, String> isbnColumn = new TableColumn<>("ISBN"); // 화면 표시 이름
		isbnColumn.setMinWidth(150); // 최소 가로 150px
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));// VO의 어떤 필드에서 값을 가져오는지 지정
		
		TableColumn<BookVO, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setMinWidth(150);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("btitle"));
		
		TableColumn<BookVO, String> authorColumn = new TableColumn<>("Author");
		authorColumn.setMinWidth(150);
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("bauthor"));
		
		TableColumn<BookVO, Integer> priceColumn = new TableColumn<>("Price");
		priceColumn.setMinWidth(150);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("bprice"));
		
		tableView = new TableView<BookVO>();
		tableView.getColumns().addAll(
				isbnColumn, titleColumn, authorColumn, priceColumn); // 컬럼 객체를 테이블 뷰에 붙임
		
		// 데이터를 TableView에 세팅
		tableView.setItems(list);
		
		root.setCenter(tableView);
		root.setBottom(flowpane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Simple JavaFX TableView");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}
