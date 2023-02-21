package step3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import step3.vo.User;

public abstract class UserDao {
	
	protected abstract Connection getConnection() throws Exception;
	
	public void insert(User user) throws Exception {
		Connection con = getConnection();
		
		// 3. Statement 객체
		String sql = "INSERT INTO users VALUES (?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getName());
		
		// 4. SQL 문 실행
		int result = pstmt.executeUpdate();
		
		// 5. 결과 처리
		if (result == 1) {
			System.out.println("인서트 성공"); // 테스트용이므로 결과 처리를 여기서 마무리했음
		}
		
		// 6. 리소스 할당 해제
		pstmt.close();
		con.close();
	}
	
	public User select(String id) throws Exception {
		Connection con = getConnection();
		
		// 3. Statement 객체
		String sql = "SELECT * FROM users WHERE id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		
		// 4. SQL 문 실행
		ResultSet result = pstmt.executeQuery();
		
		// 5. 결과 처리
		result.next();
		User user = new User(result.getString("id"),
							result.getString("password"),
							result.getString("name"));

		// 6. 리소스 할당 해제
		pstmt.close();
		con.close();
		
		return user;
	}
}
