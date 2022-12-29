/* View */

package lecture.jdbc.view;

import javafx.application.Application;
import javafx.collections.ObservableList;
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
import lecture.jdbc.controller.BookSearchByKeywordController;
import lecture.jdbc.controller.DeleteByISBNController;
import lecture.jdbc.vo.BookVO;

public class BookSearchMVC extends Application {
	private TableView<BookVO> tableView;
	private TextField textField;
	private Button deleteBtn;
	private String deleteTargetISBN;
	private String currentSearchKeyword;
	
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
			// [뷰]는 직접 로직을 수행할 수 없음
			// [컨트롤러]를 통해 [서비스 모델]에 로직 처리 요청
			currentSearchKeyword = textField.getText();
			BookSearchByKeywordController controller =
					new BookSearchByKeywordController();
			
			ObservableList<BookVO> list =
					controller.getResult(currentSearchKeyword);
			
			tableView.setItems(list);
			
			textField.clear();
		});
 
		
		// 삭제 버튼
		deleteBtn = new Button("선택된 책 삭제");
		deleteBtn.setPrefSize(150, 40);
		deleteBtn.setDisable(true);
		deleteBtn.setOnAction(e -> {
			DeleteByISBNController controller = 
					new DeleteByISBNController();
			ObservableList<BookVO> list =
					controller.getResult(deleteTargetISBN, currentSearchKeyword); // 컨트롤러 클래스는 기능 마다 존재하므로,
									  				// 메서드는 같은 이름으로 준다. getResult, do, exec 등
			tableView.setItems(list); // 성공 여부에 상관 없이 SQL 실행 후 요청한 list만 받아서 그려주면 된다
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

		});
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Book Search JavaFX MVC");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}
