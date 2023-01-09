package lecture.bookstore.service;

import javafx.collections.ObservableList;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import lecture.bookstore.dao.BookDAO;
import lecture.bookstore.vo.BookVO;

public class BookService {

	public ObservableList<BookVO> selectBooksByKeyword(String keyword) {
		BookDAO dao = new BookDAO();
		ObservableList<BookVO> list = dao.select(keyword);
		return list;
	}

	public ObservableList<BookVO> deleteBookByISBN(String isbn, String keyword) {
		BookDAO dao = new BookDAO();
		int affectedRowCount = dao.delete(isbn);
		ObservableList<BookVO> list = dao.select(keyword);
		return list;
	}

	public BookVO selectWholeBookInfoByISBN(String isbn) {
		BookDAO dao = new BookDAO();
		BookVO detailBookInfo = dao.selectOne(isbn);
		return detailBookInfo;
	}

}
