package assignment;

import java.util.HashMap;
import java.util.HashSet;

class Book {
	String category;
	String bookName;
	double bookPrice;
	double bookDiscountRate;
	
	public Book() {
		
	}

	public Book(String category, String bookName, double bookPrice, double bookDiscountRate) {
		super();
		this.category = category;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.bookDiscountRate = bookDiscountRate;
	}
	
	public String toString() {
		return this.category + "\t" +
				this.bookName + "\t" +
				this.bookPrice + "원" + "\t" +
				this.bookDiscountRate + "%";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public double getBookDiscountRate() {
		return bookDiscountRate;
	}

	public void setBookDiscountRate(double bookDiscountRate) {
		this.bookDiscountRate = bookDiscountRate;
	}
	
	
}

public class Prac9 {

	public static void main(String[] args) {
		Book[] bookArray = new Book[5];
		HashSet<String> category = new HashSet<String>();
		HashMap<String, Double> priceSumsByCat = new HashMap<String, Double>();
		HashMap<String, Double> discountedPriceSumsByCat = new HashMap<String, Double>();
		
		bookArray[0] = new Book("IT", "SQL Plus", 50000, 5);
		bookArray[1] = new Book("IT", "Java 2.0", 40000, 3);
		bookArray[2] = new Book("IT", "JSP Servlet", 60000, 6);
		bookArray[3] = new Book("Novel", "Da vinci Code", 30000, 10);
		bookArray[4] = new Book("Novel", "Cloven Hoof", 50000, 15);
		
		for (Book book : bookArray) {
			category.add(book.getCategory());
			System.out.println(book.toString());
		}
		
		System.out.println();
		
		for (String cate : category) {
			double sum = 0;
			double discountedSum = 0;
			for (Book book: bookArray) {
				if (book.getCategory().equals(cate)) {
					sum += book.getBookPrice();
					discountedSum += book.getBookPrice()
							- book.getBookPrice() * book.getBookDiscountRate() * 0.01;
				}
			}
			priceSumsByCat.put(cate, sum);
			discountedPriceSumsByCat.put(cate, discountedSum);
		}
		
		for (String cate: category) {
			System.out.println(cate + "의 책 가격 합: " + priceSumsByCat.get(cate));
			System.out.println(cate + "의 할인된 책 가격 합: " + discountedPriceSumsByCat.get(cate));
		}
	}
}
