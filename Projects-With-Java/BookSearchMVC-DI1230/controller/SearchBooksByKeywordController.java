package lecture.bookstore.controller;

import javafx.collections.ObservableList;
import lecture.bookstore.service.BookService;
import lecture.bookstore.vo.BookVO;

public class SearchBooksByKeywordController {

	public ObservableList<BookVO> exec(String keyword) {
		BookService service = new BookService();
		ObservableList<BookVO> list = service.selectBooksByKeyword(keyword);
		return list;
	}

}
