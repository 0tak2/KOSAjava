package step4.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SimpleMakeConnection {

	public Connection getConnection() throws Exception {
		// 순수 JDBC 처리
		// 1. Driver Loading
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 2. Connection 객체
		String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/spring?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
		String id = "root";
		String pw = "test1234";
		
		Connection con = DriverManager.getConnection(jdbcUrl, id, pw);
		return con;
	}
}
