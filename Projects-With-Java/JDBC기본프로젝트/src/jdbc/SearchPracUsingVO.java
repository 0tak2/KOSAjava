package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import jdbc.vo.Book;

public class SearchPracUsingVO {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("검색할 책의 키워드 입력: ");
		String keyword = sc.nextLine();
		sc.close();
		System.out.println();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbc_uri = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
			
			Connection con = DriverManager.getConnection(jdbc_uri, id, pw);
			
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("SELECT BISBN, BTITLE, BDATE, BAUTHOR, BPRICE ");
			sqlBuf.append("FROM BOOK ");
			sqlBuf.append("WHERE BTITLE LIKE ? ");
			sqlBuf.append("ORDER BY BPRICE DESC");
			
			String sql = sqlBuf.toString();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			ResultSet rs = pstmt.executeQuery();
			
			ArrayList<Book> list = new ArrayList<Book>();
			while(rs.next()) {
				Book book = new Book(rs.getString("bisbn"),
							rs.getString("btitle"),
							rs.getString("bauthor"),
							rs.getInt("bprice"));
				list.add(book);
			}
			
			System.out.println("ISBN\t\t\t제목\t\t\t저자\t\t\t 가격");
			for (Book b : list) {
				System.out.println(b);
			}
			
			if (list.size() == 0) {
				System.out.println("불러올 값이 없습니다.");
			} else {
				System.out.println("전체 " + list.size() + "건을 불러왔습니다.");
			}
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
