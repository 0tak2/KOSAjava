package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 특정 책을 지우는 예제
public class DeleteExam {
	public static void main(String[] args) {
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbc_uri = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
			
			// 2. 데이터베이스 연결
			Connection con = DriverManager.getConnection(jdbc_uri, id, pw);
			
			// 3. PreparedStatement 생성
			String sql = "DELETE FROM book WHERE btitle LIKE ?"; // 특정 키워드가 들어간 책을 지우려고 한다
			String keyword = "여행";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			
			// 4. SQL 실행
			// DELETE 구문이므로 executeQuery()가 아닌 executeUpdate() 호출
			// 이때 리턴 값은 int로, 영향을 받은 로우 수가 반환됨.
			// ex> 4개의 로우가 삭제/수정되면 4가 반환됨.
			int count = pstmt.executeUpdate(); 
			
			// 5. 결과 처리
			System.out.println("삭제한 로우의 수는: " + count);
			
			// 6. 자원 할당 해제
			pstmt.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
