package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.vo.Department;

public class VoPrac {
	public static void main(String[] args) {
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 데이터베이스 연결 (mysql_test_db)
			String jdbcUri = "jdbc:mysql://127.0.0.1:3306/mysql_test_db?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "test1234";
			Connection con = DriverManager.getConnection(jdbcUri, id, pw);
			
			// 3. PreparedStatement 생성
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("SELECT CATEGORY, DEPARTMENT_NAME, CAPACITY ");
			sqlBuf.append("FROM TB_DEPARTMENT ");
			sqlBuf.append("WHERE CATEGORY = ? ");
			sqlBuf.append("AND CAPACITY BETWEEN ? AND ? ");
			sqlBuf.append("ORDER BY DEPARTMENT_NAME");
			String sql = sqlBuf.toString();
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "공학");
			pstmt.setInt(2, 20);
			pstmt.setInt(3, 30);
			
			// 4. SQL 실행
			ResultSet rs = pstmt.executeQuery();
			
			// 5. 결과 처리
			ArrayList<Department> list = new ArrayList<Department>();
			while (rs.next()) {
				Department dp = new Department(rs.getString("category"),
												rs.getString("department_name"),
												rs.getInt("capacity"));
			}			
			System.out.println("계열\t학과이름\t정원");
			for (Department dp : list) {
				System.out.println(dp);
			}
			
			// 6. 할당 해제
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

