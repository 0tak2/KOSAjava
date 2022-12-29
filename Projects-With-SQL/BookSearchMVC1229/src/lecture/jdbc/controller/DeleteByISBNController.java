package lecture.jdbc.controller;

import javafx.collections.ObservableList;
import lecture.jdbc.service.BookService;
import lecture.jdbc.vo.BookVO;

public class DeleteByISBNController {

	public ObservableList<BookVO> getResult(String deleteTargetISBN, String currentSearchKeyword) {
		BookService service = new BookService();
		ObservableList<BookVO> list =
				service.deleteByISBN(deleteTargetISBN, currentSearchKeyword);
		return list;
	}

}
