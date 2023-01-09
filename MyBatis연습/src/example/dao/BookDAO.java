package example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import example.vo.BookVO;

public class BookDAO {

	private SqlSessionFactory factory;
	
	public BookDAO() {
	}
	
	public BookDAO(SqlSessionFactory factory) {
		this.factory = factory; // SqlSessionFactory의 객체 주입
	}

	public HashMap<String, Object> selectByISBNHashMap(String isbn) {
		HashMap<String, Object> map = null;
		
		SqlSession session = factory.openSession(); // 팩토리 객체로부터 세션을 얻음
		
		// selectOne 메서드는 한 개의 로우만을 가져옴
		// SQL 구문은 Mapper XML 내에 작성
		// 첫 번째 인자로 Mapper 내의 ID를, 두 번쨰 인자로 패러미터를 넘겨줌
		map = session.selectOne("example.myBook.selectBookByISBNHashMap", isbn);
		
		return map;
	}

	public List<HashMap<String, Object>> selectAllHashMap() {
		List<HashMap<String, Object>> list = null;
		
		SqlSession session = factory.openSession();
		
		// selectList 메서드는 여러 로우를 List로 가져옴
		list = session.selectList("example.myBook.selectAllHashMap");
		
		return list;
	}

	public BookVO selectByISBNBookVO(String isbn) {
		BookVO book = null;
		
		SqlSession session = factory.openSession();
		
		book = session.selectOne("example.myBook.selectBookByISBNBookVO", isbn);
		
		return book;
	}

	public BookVO selectByISBNResultMap(String isbn) {
		BookVO book = null;
		
		SqlSession session = factory.openSession();
		
		try {
			book = session.selectOne("example.myBook.selectBookByISBNResultMap", isbn);
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		
		return book;
	}

	public int updateByISBN(BookVO book) {
		int result = 0;
		
		SqlSession session = factory.openSession();
		
		try {
			result = session.update("example.myBook.update", book);
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}
		
		return result;
	}

}
