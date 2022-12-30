package lecture.jdbc.di.step4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 상속도 안되고, DAO 안에 연결 정보를 박을 수도 없다
// 그렇다면 연결 정보를 별도의 Class로 분리하여 취급해보자
public class UserDAO {

	private SimpleConnectionMaker simpleConnectionMaker;
	
	public UserDAO() {
		simpleConnectionMaker = new SimpleConnectionMaker();
	}
	
	public void insert(User user) {
		try {
			Connection con = simpleConnectionMaker.getConnection();
			
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
			Connection con = simpleConnectionMaker.getConnection();
			
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
