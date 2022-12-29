/* 콘솔 환경에서 DBCP를 테스트해본다. */

package testmain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class TestMain {
	
	private static BasicDataSource basicDS;
	
	// main 메서드 실행 전 실행되는 코드 블럭
	// 보통 리소스 로딩에 사용
	static {
		// 메인이 호출되기 전에 커넥션 풀 구성
		basicDS = new BasicDataSource();
		basicDS.setDriverClassName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로드
		basicDS.setUrl("jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
		basicDS.setUsername("root");
		basicDS.setPassword("test1234"); // DBMS 연결 정보 지정 완료
		basicDS.setInitialSize(10); // 초기 풀 크기 지정 (처음에 10개의 커넥션 설정)
		basicDS.setMaxTotal(20); // 최대 풀 크기 지정;
									// 즉 커넥션 수가 더 필요하면 커넥션을 더 늘릴 수 있으며, 여기서 그 한도를 지정하였음.
	}
	
	public static DataSource getDataSource() {
		// DataSource는 BasicDataSource가 구현한 인터페이스
		return basicDS;
	}
	
	public static void main(String[] args) {
		DataSource ds = getDataSource(); // 커넥션 풀을 가져옴
		Connection con = null;
		try {
			con = ds.getConnection(); // 커넥션 풀에서 커넥션을 빌려옴
			String sql = "SELECT btitle, bauthor FROM book";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("btitle"));
			}
			rs.close();
			pstmt.close();
			con.close(); // 연결이 끊어지지 않고, 커넥션 풀에 반납됨
			((BasicDataSource)(getDataSource())).close(); // 커넥션 풀 닫기
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
