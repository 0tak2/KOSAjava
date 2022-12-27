package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 특정 책을 지우는 예제
public class DeleteExamWithTransaction {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbc_uri = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
			
			// DB 접속
			Connection con = DriverManager.getConnection(jdbc_uri, id, pw);
			con.setAutoCommit(false); // 트랜잭션 시작
			
			String sql = "DELETE FROM book WHERE btitle LIKE ?";
			String keyword = "여행";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			
			int count = pstmt.executeUpdate(); 
			
			// 결과 처리
			System.out.println("삭제한 로우의 수는: " + count);
			/* con.commit(); // 트랜잭션을 종료하고 지금까지 실행한 SQL 영구 적용 */
			con.rollback(); // 트랜잭션을 종료하고 지금까지 실행한 SQL 무효화
			System.out.println("롤백됨");
			
			// 만약, 트랜잭션을 종료(커밋, 롤백)하지 않고,
			// 커넥션을 닫으려고 하면(con.close()), 그 시점에서 커밋되고 커넥션이 닫힘.
			pstmt.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
