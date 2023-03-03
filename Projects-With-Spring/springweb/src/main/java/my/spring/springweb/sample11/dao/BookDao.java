package my.spring.springweb.sample11.dao;

import java.util.List;

import my.spring.springweb.sample11.vo.Book;

public interface BookDao {

	int getBookCount();
	
	List<Book> gettAllBooks();
	
	List<Book> getSearchBooks(String keyword);
}
