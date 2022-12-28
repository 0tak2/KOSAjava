package lecture.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lecture.jdbc.vo.BookVO;

public class BookSearchJavaFXWithDelete extends Application {
	private TableView<BookVO> tableView; // VO 클래스를 제네릭으로 지정. 한 로우가 하나의 VO에 대응.
	private TextField textField;
	private Button deleteBtn;
	private String deleteTargetISBN;
	
	Connection con;
	
	private void getBook(String keyword) {
		try {
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("SELECT BISBN, BTITLE, BDATE, BAUTHOR, BPRICE ");
			sqlBuf.append("FROM BOOK ");
			sqlBuf.append("WHERE BTITLE LIKE ? ");
			sqlBuf.append("ORDER BY BPRICE DESC");
			
			String sql = sqlBuf.toString();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			
			ResultSet rs = pstmt.executeQuery();
			
			ObservableList<BookVO> list = FXCollections.observableArrayList();
			
			while(rs.next()) {
				BookVO book = new BookVO(rs.getString("bisbn"),
							rs.getString("btitle"),
							rs.getString("bauthor"),
							rs.getInt("bprice"));
				list.add(book);
			}
			
			tableView.setItems(list);
			
			rs.close();
			pstmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public BookSearchJavaFXWithDelete() {
		// 생성자에서 준비 단계
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 1단계 드라이버 로딩
			String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
			con = DriverManager.getConnection(jdbcUrl, id, pw);  // 2단계 접속
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		// 검색창
		textField = new TextField();
		textField.setPrefSize(250, 40);
		textField.setOnAction(e -> {
			getBook(textField.getText());
		});
		
		// 삭제 버튼
		deleteBtn = new Button("선택된 책 삭제");
		deleteBtn.setPrefSize(150, 40);
		deleteBtn.setDisable(true); // 초기에는 비활성화한다
		deleteBtn.setOnAction(e -> {
			Alert alert = new Alert(AlertType.WARNING, 
						            "한 번 삭제하면 돌이킬 수 없습니다. 정말 삭제하시겠습니까?",
						             ButtonType.OK, 
						             ButtonType.CANCEL);
			alert.setTitle("삭제 경고");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.CANCEL) {
			    return;
			}
			
			try {
				StringBuffer sqlBuf = new StringBuffer();
				sqlBuf.append("DELETE ");
				sqlBuf.append("FROM BOOK ");
				sqlBuf.append("WHERE BISBN = ? ");
				
				String sql = sqlBuf.toString();
				
				con.setAutoCommit(false); // 트랜잭션 시작
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, deleteTargetISBN);
				
				int affectedRowsCount = pstmt.executeUpdate();
				if (affectedRowsCount == 1) {
					con.commit();
					Alert alert2 = new Alert(AlertType.INFORMATION,
											"삭제가 완료되었습니다.");
					alert2.setTitle("삭제 완료");
					alert2.showAndWait();
					
					// 삭제 내용 반영
					getBook(textField.getText());
				} else {
					con.rollback();
					Alert alert3 = new Alert(AlertType.ERROR,
							"삭제 중 문제가 발생했습니다.");
					alert3.setTitle("삭제 중 오류 발생");
					alert3.showAndWait();
				}
				
				pstmt.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		});
		
		flowpane.getChildren().add(textField);
		flowpane.getChildren().add(deleteBtn);
		
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
		
		// tableView 각 행에 대해 특성 설정 가능.
		// 대표적으로 이벤트.
		tableView.setRowFactory(e1 -> { // 람다로 인터페이스 재정의
			// 각 행에 대한 TableRow 객체를 만들고,
			TableRow<BookVO> row = new TableRow<>();
			
			// 각 행에 대해 특성을 설정한 후, TableRow 객체를 반환해야함
			row.setOnMouseClicked(e2 -> {
				deleteBtn.setDisable(false); // 버튼 활성화
				BookVO book = row.getItem(); // 행에 맵핑된 객체를 반환. 어떤 행을 클릭했는지 식별 가능.

				deleteTargetISBN = book.getBisbn(); 
			});
			return row;
		});
		
		root.setCenter(tableView);
		root.setBottom(flowpane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Simple JavaFX TableView");
		
		primaryStage.setOnCloseRequest(e -> { // 창이 닫힐 때 실행
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}
