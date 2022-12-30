package lecture.jdbc.di.step3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDAO {
	
	protected abstract Connection getConnection();
	// 추상 메서드로 잡아서 상속 받아 오버라이딩할 수 있도록 정의
	// private이면 오버라이딩 불가. protected로 지정하여 자손
	// 클래스에서 오버라이딩 할 수 있도록 처리
	// protected: 패키지가 달라도 상속 관계에 있으면 접근 가능
	
	public void insert(User user) {
		try {
			Connection con = getConnection();
			
			String sql = "INSERT INTO users VALUES (?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public User select(String findingID) {
		
		User user = null;
		
		try {
			Connection con = getConnection();
			
			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, findingID);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			user = new User(rs.getString("id"), rs.getString("name"),
								rs.getString("password"));
			
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
