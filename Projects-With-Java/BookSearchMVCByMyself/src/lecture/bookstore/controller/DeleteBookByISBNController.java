package lecture.bookstore.controller;

import javafx.collections.ObservableList;
import lecture.bookstore.service.BookService;
import lecture.bookstore.vo.BookVO;

public class DeleteBookByISBNController {

	public ObservableList<BookVO> exec(String isbn, String keyword) {
		BookService service = new BookService();
		ObservableList<BookVO> list = service.deleteBookByISBN(isbn, keyword);
		return list;
	}

}
