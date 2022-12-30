package lecture.jdbc.di.step6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private ConnectionMaker connectionMaker;
	
	public UserDAO(ConnectionMaker connectionMaker) { // 객체를 받아 필드에 할당
		this.connectionMaker = connectionMaker;        // 더 이상 클래스 타입이 명시되지 않음
	}
	
	public void insert(User user) {
		try {
			Connection con = connectionMaker.getConnection();
			
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
			Connection con = connectionMaker.getConnection();
			
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
