package lecture.bookstore.view;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lecture.bookstore.controller.DeleteBookByISBNController;
import lecture.bookstore.controller.SelectDetailBookInfoController;
import lecture.bookstore.controller.SearchBooksByKeywordController;
import lecture.bookstore.vo.BookVO;

public class BookSearchFX extends Application {
	private TableView<BookVO> tableView;
	private TextField textField;
	private Button deleteBtn;
	private String currentKeyword;
	private String deleteTargetISBN;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);
		
		FlowPane flowpane = new FlowPane();
		flowpane.setPadding(new Insets(10, 10, 10, 10));
		flowpane.setColumnHalignment(HPos.CENTER);
		flowpane.setPrefSize(700, 40);
		flowpane.setHgap(10);
		
		// 텍스트 필드
		textField = new TextField();
		textField.setPrefSize(250, 40);
		
		textField.setOnAction(e -> {
			currentKeyword = textField.getText();
			
			SearchBooksByKeywordController controller =
					new SearchBooksByKeywordController();
			
			ObservableList<BookVO> list = controller.exec(currentKeyword);
			
			tableView.setItems(list);
			textField.clear();
		});
 
		
		// 삭제 버튼
		deleteBtn = new Button("선택된 책 삭제");
		deleteBtn.setPrefSize(150, 40);
		deleteBtn.setDisable(true);
		deleteBtn.setOnAction(e -> {
			DeleteBookByISBNController controller =
					new DeleteBookByISBNController();
			ObservableList<BookVO> list = controller.exec(deleteTargetISBN, currentKeyword);
			
			tableView.setItems(list);
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
		
		tableView.setRowFactory(e -> {
			TableRow<BookVO> row = new TableRow<>();
			
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) { // 더블클릭
					deleteBtn.setDisable(false);
		        	BookVO book = row.getItem();
		        	String selectedISBN = book.getBisbn();
		        	
		        	SelectDetailBookInfoController controller =
		        			new SelectDetailBookInfoController();
		        	
		        	BookVO detailBook = controller.exec(selectedISBN);
		        	
		        	StringBuffer bookInfoStrBuff = new StringBuffer();
		    		bookInfoStrBuff.append("ISBN: " + detailBook.getBisbn() + "\n");
		    		bookInfoStrBuff.append("Title: " + detailBook.getBtitle() + "\n");
		    		bookInfoStrBuff.append("Price: " + detailBook.getBprice() + "\n");
		    		bookInfoStrBuff.append("Author: " + detailBook.getBauthor() + "\n");
		    		bookInfoStrBuff.append("Translator: " + detailBook.getBtranslator() + "\n");
		    		bookInfoStrBuff.append("Date: " + detailBook.getBdate() + "\n");
		    		bookInfoStrBuff.append("Page: " + detailBook.getBpage() + "\n");
		    		bookInfoStrBuff.append("Supplement: " + detailBook.getBsupplement());
		    		
		    		Dialog<String> dialog = new Dialog<String>();
		        	dialog.setTitle("도서 정보");
		        	ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		            dialog.setContentText(bookInfoStrBuff.toString());
		            dialog.getDialogPane().getButtonTypes().add(type);
		        	dialog.showAndWait();
		        } else if (event.getClickCount() == 1 && (!row.isEmpty())) {
		        	deleteBtn.setDisable(false);
		        	BookVO book = row.getItem();
		        	deleteTargetISBN = book.getBisbn();
		        }				
			});
			
			return row;
		});
		
		root.setCenter(tableView);
		root.setBottom(flowpane);
		
		Scene scene = new Scene(root);
		
		primaryStage.setOnCloseRequest(e1 -> {
			//
		});
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Book Search JavaFX MVC");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}
