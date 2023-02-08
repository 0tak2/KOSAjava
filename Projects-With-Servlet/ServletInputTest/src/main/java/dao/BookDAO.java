package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.BookVO;

public class BookDAO {

	private SqlSession session;
	
	public BookDAO() {
	}
	
	public BookDAO(SqlSession session) {
		super();
		this.session = session;
	}

	public List<BookVO> select(BookVO target) {
		List<BookVO> list = null;

		try {
			list = session.selectList("liverary.book.select", target);			
		} catch (Exception e) {
			throw e;
		}
		
		return list;
	}
}
