package example.main;

import org.apache.ibatis.session.SqlSessionFactory;

import example.dao.BookDAO;
import example.mybatis.MyBatisConnectionFactory;
import example.vo.BookVO;

public class Main {

	public static void main(String[] args) {
		
		SqlSessionFactory factory = 
				MyBatisConnectionFactory.getSqlSessionFactory();
		
		BookDAO dao = new BookDAO(factory);
		
		
		// 3. 책의 ISBN을 이용해 책 1권의 데이터를 가져와 출력하되,
		//    HashMap이 아니라 VO를 이용
		BookVO book1 = dao.selectByISBNBookVO("89-7914-206-4");
		System.out.println(book1.getBtitle() + ", " + book1.getBauthor());
		
		// 4. 책의 ISBN을 이용해 책 1권의 데이터를 가져와 출력하되,
		//    HashMap이 아니라 VO를 이용
		BookVO book2 = dao.selectByISBNResultMap("89-7914-206-4");
		System.out.println(book2.getBtitle() + ", " + book2.getBauthor());
		
		// 4. 책의 ISBN을 이용해 책 1권의 데이터를 업데이트
		BookVO book = new BookVO();
		book.setBisbn("89-7914-206-4");
		book.setBtitle("자바 30일 완성");
		book.setBauthor("신사임당");
		book.setBprice(18000);
		
		int result = dao.updateByISBN(book);
		
		System.out.println("영향을 받은 행의 수: " + result);
	}
}
