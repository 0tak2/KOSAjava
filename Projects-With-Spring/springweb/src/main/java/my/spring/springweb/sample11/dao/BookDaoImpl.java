package my.spring.springweb.sample11.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.spring.springweb.sample11.vo.Book;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private SqlSession session;
	// SqlSession은 SqlSessionTemplate 상위 인터페이스. 타입을 기준으로 주입되게 됨.
	
	@Override
	public int getBookCount() {
		return session.selectOne("myBook.countBooks");
	}

	@Override
	public List<Book> gettAllBooks() {
		List<Book> list = session.selectList("myBook.selectAll");
		return list;
	}

	@Override
	public List<Book> getSearchBooks(String keyword) {
		List<Book> list = session.selectList("myBook.selectBookByKeyword", keyword);
		return list;
	}

}
