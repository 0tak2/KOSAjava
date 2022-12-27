package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		try {
			// 1. JDBC 드라이버 로딩 단계
			// Driver 클래스의 풀패키지 위치를 지정하여 해당 클래스를 찾는다.
			Class.forName("com.mysql.cj.jdbc.Driver");			
			System.out.println("Driver Loaded Successfully");
			
			// 2. 데이터베이스 접속; URI는 DBMS마다 상이함.
			// ?뒤에 옵션 지정. MySQL 문서 참조.
			String jdbc_uri = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root"; // 실제 개발에는 root를 사용하지 않는다. 테스트로만 사용할 것.
			String pw = "test1234";
			// Java가 제공하는 공통 인터페이스인 DriverManager 이용.
			Connection con = DriverManager.getConnection(jdbc_uri, id, pw); // getConnection은 Static 메서드; Connection 객체 반환
			System.out.println("Connected Successfully");
			
			// 3. Statement 생성
			Statement stmt = con.createStatement(); //java.sql.Statement
			
			// 4. Query 작성 및 실행
			String sql = "SELECT BISBN, BTITLE, BDATE, BAUTHOR, BPRICE FROM BOOK"; // DELIMETER ";"는 포함하지 않음
			ResultSet rs = stmt.executeQuery(sql);
			
			// 5. 결과 처리
			rs.next();
			String title = rs.getString("btitle"); // 현재 커서가 가리키는 로우의 특정 컬럼의 값을 String으로 가져옴
			int priceInt = rs.getInt("bprice");
			String priceStr = rs.getString("bprice"); // 가능하면 맞춰주는 것이 좋지만 정수 타입 데이터도 String으로 받을 수 있다.
			System.out.println("책 제목은: " + title);
			System.out.println("책 가격은: " + priceInt + " (Integer)");
			System.out.println("책 가격은: " + priceStr + " (String)");
			
			// 컬럼 번호로 가져올 수도 있다. (1부터 시작) 그러나 명확하게 컬럼명을 지정하는 것이 좋다.
			String author = rs.getString(4);
			System.out.println("책 저자는: " + author);
			System.out.println();
			
			// 결과 셋 반복
			while(rs.next()) { // 데이터가 없을 때까지 반복
				String t = rs.getString("btitle");
				int p = rs.getInt("bprice");
				String a = rs.getString("bauthor");
				System.out.println("책 제목은: " + t);
				System.out.println("책 가격은: " + p);
				System.out.println("책 저자는: " + a);
				System.out.println();
			}
			
			// 책 제목에 '여행'이 들어간 책 찾기
			System.out.println("키워드: 여행");
			String sql2 = "SELECT BISBN, BTITLE, BDATE, BAUTHOR, BPRICE FROM BOOK WHERE BTITLE LIKE '%여행%'";
			rs = stmt.executeQuery(sql2);
			while(rs.next()) {
				String t = rs.getString("btitle");
				int p = rs.getInt("bprice");
				String a = rs.getString("bauthor");
				System.out.println("책 제목은: " + t);
				System.out.println("책 가격은: " + p);
				System.out.println("책 저자는: " + a);
				System.out.println();
			}
			
			// PreparedStatement 사용
			// PreparedStatement는 받아올 때 먼저 SQL을 만들어서 넣어줘야 한다.
			// 채워지지 않은 부분을 ?로 넣는다. 이를 In Parameter라고 한다. 1개 이상 넣을 수 있다.
			// 주의: 테이블명과 같이 주요 Keyword에는 In Parameter를 지정할 수 없다.
			String keyword = "자바";
			System.out.println("키워드: " + keyword);
			
			String sql3 = "SELECT BISBN, BTITLE, BDATE, BAUTHOR, BPRICE FROM BOOK WHERE BTITLE LIKE ?";
			PreparedStatement pstmt = con.prepareStatement(sql3);
			pstmt.setString(1, "%" + keyword + "%"); // 첫번쨰 인파라미터의 값을 지정
			rs = pstmt.executeQuery(); // 객체 자체가 SQL을 들고 있으므로 인자 없음
			while(rs.next()) {
				String t = rs.getString("btitle");
				int p = rs.getInt("bprice");
				String a = rs.getString("bauthor");
				System.out.println("책 제목은: " + t);
				System.out.println("책 가격은: " + p);
				System.out.println("책 저자는: " + a);
				System.out.println();
			}
			
			// 6. 사용 자원 할당 해제
			rs.close();
			pstmt.close();
			stmt.close();
			con.close();
			System.out.println("Closed Successfully");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
