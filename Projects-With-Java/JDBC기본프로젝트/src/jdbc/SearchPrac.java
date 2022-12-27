package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchPrac {
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
			
			/*
			String sql = "SELECT BISBN, BTITLE, BDATE, BAUTHOR, BPRICE FROM BOOK WHERE BTITLE LIKE ? ORDER BY BPRICE DESC";
			*/
			
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("SELECT BISBN, BTITLE, BDATE, BAUTHOR, BPRICE ");
			sqlBuf.append("FROM BOOK ");
			sqlBuf.append("WHERE BTITLE LIKE ? ");
			sqlBuf.append("ORDER BY BPRICE DESC");
			
			String sql = sqlBuf.toString();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			ResultSet rs = pstmt.executeQuery();
			
			System.out.println("순번 \t ISBN \t\t\t 제목 \t\t\t 저자 \t\t\t 가격");
			int index = 0;
			while(rs.next()) {
				String i = rs.getString("bisbn");
				String t = rs.getString("btitle");
				String a = rs.getString("bauthor");
				int p = rs.getInt("bprice");
				System.out.printf("%d \t %s \t %s \t %s \t %d%n", ++index, i, t, a, p);
			}
			if (index == 0) {
				System.out.println("불러올 값이 없습니다.");
			} else {
				System.out.println("전체 " + index + "건을 불러왔습니다.");
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
