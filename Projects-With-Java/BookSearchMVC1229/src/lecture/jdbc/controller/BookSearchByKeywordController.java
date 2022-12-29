/* Controller */
package lecture.jdbc.controller;

import javafx.collections.ObservableList;
import lecture.jdbc.service.BookService;
import lecture.jdbc.vo.BookVO;

public class BookSearchByKeywordController {

	public ObservableList<BookVO> getResult(String keyword) { // "getResult"
		// 뷰와 서비스 (모델)과의 연결
		BookService service = new BookService();
		
		ObservableList<BookVO> list =
				service.selectBooksByKeyword(keyword); // 메서드 하나를 트랜잭션 단위 하나로 설계
														   // 따라서 트랜잭션을 대표할 수 있게 명명
														   // "selectBooksByKeyword"
		return list;
	}

}
