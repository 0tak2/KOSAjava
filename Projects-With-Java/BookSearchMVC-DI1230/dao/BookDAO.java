package lecture.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lecture.bookstore.vo.BookVO;

public class BookDAO {
	private static BasicDataSource basicDS;
	static {
		basicDS = new BasicDataSource();
		basicDS.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDS.setUrl("jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
		basicDS.setUsername("root");
		basicDS.setPassword("test1234");
		basicDS.setInitialSize(10);
		basicDS.setMaxTotal(20); 
	}
	
	public static DataSource getDataSource() {
		return basicDS;
	}
	
	public ObservableList<BookVO> select(String keyword) {
		ObservableList<BookVO> list = null;
		Connection con = null;
		DataSource ds = getDataSource();
		try {
			con = ds.getConnection(); // 커넥션 풀에서 커넥션 가져오기
			
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("SELECT BISBN, BTITLE, BDATE, BAUTHOR, BPRICE ");
			sqlBuf.append("FROM BOOK ");
			sqlBuf.append("WHERE BTITLE LIKE ? ");
			sqlBuf.append("ORDER BY BPRICE DESC");
			
			String sql = sqlBuf.toString();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			
			ResultSet rs = pstmt.executeQuery();
			
			list = FXCollections.observableArrayList();
			
			while(rs.next()) {
				BookVO book = new BookVO(rs.getString("bisbn"),
							rs.getString("btitle"),
							rs.getString("bauthor"),
							rs.getInt("bprice"));
				list.add(book);
			}
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	public int delete(String isbn) {
		Connection con = null;
		DataSource ds = getDataSource();
		try {
			con = basicDS.getConnection();
			
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("DELETE ");
			sqlBuf.append("FROM BOOK ");
			sqlBuf.append("WHERE BISBN = ? ");
			
			String sql = sqlBuf.toString();
			
			con.setAutoCommit(false);
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			
			int affectedRowsCount = pstmt.executeUpdate();
			if (affectedRowsCount == 1) {
				con.commit();
			} else {
				con.rollback();
			}
			
			pstmt.close();
			con.close();
			
			return affectedRowsCount;
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return -1;
	}

	public BookVO selectWhole(String isbn) {
		BookVO detailBook = null;
		Connection con = null;
		DataSource ds = getDataSource();
		try {
			con = ds.getConnection(); // 커넥션 풀에서 커넥션 가져오기
			
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("SELECT * ");
			sqlBuf.append("FROM BOOK ");
			sqlBuf.append("WHERE BISBN = ? ");
			
			String sql = sqlBuf.toString();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  isbn);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			detailBook = new BookVO(rs.getString("bisbn"),
										rs.getString("btitle"),
										rs.getInt("bprice"),
										rs.getString("bauthor"),
										rs.getString("btranslator"),
										rs.getString("bpublisher"),
										rs.getString("bdate"),
										rs.getString("bpage"),
										rs.getString("bsupplement"));
			
			rs.close();
			pstmt.close();
			con.close();
			return detailBook;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
