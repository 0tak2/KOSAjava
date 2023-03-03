package my.spring.springweb.sample11.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	private String bookIsbn;
	private String bookTitle;
	private String bookAuthor;
	private int bookPrice;
}
