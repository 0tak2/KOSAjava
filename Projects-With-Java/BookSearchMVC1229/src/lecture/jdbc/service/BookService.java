/* Service (Model) */
package lecture.jdbc.service;

import javafx.collections.ObservableList;
import lecture.jdbc.dao.BookDAO;
import lecture.jdbc.vo.BookVO;

// 로직 담당
public class BookService {

	public ObservableList<BookVO> selectBooksByKeyword(String keyword) {
		// 키워드를 받아 SQL을 실행하여 ObservableList<BookVO>를 반환하는
		// 하나의 트랜잭션
		
		// 이 작업을 위한 로직처리 (데이터 가공, for, if, ...)가 여기에 작성되어야 함.
		// 우리 프로젝트는 간단해서 특별한 로직처리는 필요없음.
		// DBMS로부터의 결과를 받는 것으로 끝.
		
		// 이때 DB 처리는 비즈니스 계층과 별개인 영속성 계층에 해당하므로
		// DAO로 분리되어야 함.
		
		BookDAO dao = new BookDAO();
		ObservableList<BookVO> list = 
				dao.select(keyword); // DAO의 메서드는 SELECT, INSERT, ... 로 명명하는 것이 자연스러움
					   		   		   // DB처리 전담이기 때문에 로직과는 분리되어야 함.
					   		           // 나쁜 예: getResult / searchByKeyword
		return list;
	}

	public ObservableList deleteByISBN(String deleteTargetISBN, String currentSearchKeyword) {
		// 따로 처리할 로직이 없음
		BookDAO dao = new BookDAO();
		int affectedRowsCount = dao.delete(deleteTargetISBN);
		ObservableList<BookVO> list = dao.select(currentSearchKeyword);
		return list;
	}
	
}
