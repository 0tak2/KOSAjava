package lecture.bookstore.service;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import lecture.bookstore.dao.BookDAO;
import lecture.bookstore.dao.DBCPConnectionPool;
import lecture.bookstore.vo.BookVO;

public class BookService {

	public ObservableList<BookVO> selectBooksByKeyword(String keyword) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BookDAO dao = new BookDAO(con);
		ObservableList<BookVO> list = dao.select(keyword);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ObservableList<BookVO> deleteBookByISBN(String isbn, String keyword) {
		// 트랜잭션 시작 위치 -> 여기서 커넥션 객체 필요
		// 커넥션 객체에서 트랜잭션 걸고,
		// delete(), select() 모두 같은 커넥션을 사용하도록 해야함
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
			con.setAutoCommit(false); //  [트랜잭션 시작]
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		BookDAO dao = new BookDAO(con);
		int affectedRowCount = dao.delete(isbn);
		ObservableList<BookVO> list = dao.select(keyword);
		
		// 커밋 혹은 롤백
		if (affectedRowCount == 1 && list != null) {
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} //  [트랜잭션 끝]
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public BookVO selectWholeBookInfoByISBN(String isbn) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BookDAO dao = new BookDAO(con);
		BookVO detailBookInfo = dao.selectWhole(isbn);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return detailBookInfo;
	}

}
