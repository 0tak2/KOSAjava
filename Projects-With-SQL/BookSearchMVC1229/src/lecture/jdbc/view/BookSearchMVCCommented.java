/* View */

package lecture.jdbc.view;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lecture.jdbc.vo.BookVO;

public class BookSearchMVCCommented extends Application {
	private TableView<BookVO> tableView;
	private TextField textField;
	private Button deleteBtn;
	private String deleteTargetISBN;
	
	/*
	private static BasicDataSource basicDS;
	static {
		// 메인이 호출되기 전에 커넥션 풀 구성
		basicDS = new BasicDataSource();
		basicDS.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDS.setUrl("jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
		basicDS.setUsername("root");
		basicDS.setPassword("test1234");
		basicDS.setInitialSize(10);
		basicDS.setMaxTotal(20); 
	}
	
	public static DataSource getDataSource() { // 커넥션 풀 Getter
		return basicDS;
	} // -> 
	*/
	
	/*
	private void getBook(String keyword) {
		Connection con = null;
		try {
			con = basicDS.getConnection(); // 커넥션 풀에서 커넥션 가져오기
			
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
			con.close(); // 커넥션 반납
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private int deleteBook(String isbn) {
		Connection con = null;
		try {
			con = basicDS.getConnection(); // 커넥션 풀에서 커넥션 가져오기
			
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
				getBook(textField.getText());
			} else {
				con.rollback();
			}
			
			pstmt.close();
			con.close(); // 커넥션 반납
			
			return affectedRowsCount;
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return -1;
	}
	*/ // -> 
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		FlowPane flowpane = new FlowPane();
		flowpane.setPadding(new Insets(10, 10, 10, 10));
		flowpane.setColumnHalignment(HPos.CENTER);
		flowpane.setPrefSize(700, 40);
		flowpane.setHgap(10);
		
		textField = new TextField();
		textField.setPrefSize(250, 40);
		
		textField.setOnAction(e -> {
			/*
			getBook(textField.getText());
			*/ // -> Model
		});
 
		
		// 삭제 버튼
		deleteBtn = new Button("선택된 책 삭제");
		deleteBtn.setPrefSize(150, 40);
		deleteBtn.setDisable(true);
		deleteBtn.setOnAction(e -> {
			/*
			Alert alert = new Alert(AlertType.WARNING, 
						            "한 번 삭제하면 돌이킬 수 없습니다. 정말 삭제하시겠습니까?",
						             ButtonType.OK, 
						             ButtonType.CANCEL);
			alert.setTitle("삭제 경고");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.CANCEL) {
			    return;
			}
			
			int affectedRowsCount = deleteBook(deleteTargetISBN);
			if (affectedRowsCount == 1) {
				Alert alert2 = new Alert(AlertType.INFORMATION,
										"삭제가 완료되었습니다.");
				alert2.setTitle("삭제 완료");
				alert2.showAndWait();
			} else {
				Alert alert3 = new Alert(AlertType.ERROR,
						"삭제 중 문제가 발생했습니다.");
				alert3.setTitle("삭제 중 오류 발생");
				alert3.showAndWait();
			}
			*/ // ->
		});
		
		flowpane.getChildren().add(textField);
		flowpane.getChildren().add(deleteBtn);
		
		TableColumn<BookVO, String> isbnColumn = new TableColumn<>("ISBN");
		isbnColumn.setMinWidth(150);
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
		
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
				isbnColumn, titleColumn, authorColumn, priceColumn);
		
		tableView.setRowFactory(e1 -> {
			TableRow<BookVO> row = new TableRow<>();
			
			row.setOnMouseClicked(e2 -> {
				deleteBtn.setDisable(false);
				BookVO book = row.getItem();
				
				if (book != null) {
					deleteTargetISBN = book.getBisbn(); 
				}
			});
			return row;
		});
		
		root.setCenter(tableView);
		root.setBottom(flowpane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setOnCloseRequest(e1 -> {
			/*
			try {
				((BasicDataSource)(getDataSource())).close(); // 종료시 커넥션 풀을 닫는다
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			*/ // -> 
		});
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Book Search JavaFX MVC");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}
