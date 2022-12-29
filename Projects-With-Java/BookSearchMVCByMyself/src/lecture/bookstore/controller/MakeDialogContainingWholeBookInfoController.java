package lecture.bookstore.controller;

import lecture.bookstore.service.BookService;
import lecture.bookstore.vo.BookVO;

public class MakeDialogContainingWholeBookInfoController {

	public BookVO exec(String isbn) {
		BookService service = new BookService();
		BookVO detailBook = service.selectWholeBookInfoByISBN(isbn);
		return detailBook;
	}

}
