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
	Connection con;
	
	public BookDAO() {
	}
	
	public BookDAO(Connection con) { // 의존성을 주입받는 생성자
		super();
		this.con = con;
	}

	public ObservableList<BookVO> select(String keyword) {
		ObservableList<BookVO> list = null;
		try {	
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
			// con.close(); // -> 아직 트랜잭션이 종료되지 않음
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	public int delete(String isbn) {
		int affectedRowsCount = 0;
		
		try {
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("DELETE ");
			sqlBuf.append("FROM BOOK ");
			sqlBuf.append("WHERE BISBN = ? ");
			
			String sql = sqlBuf.toString();
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			
			affectedRowsCount = pstmt.executeUpdate();
			/*
			if (affectedRowsCount == 1) {
				con.commit();
			} else {
				con.rollback(); // -> 서비스에서 롤백 혹은 커밋
			}
			
			pstmt.close();
			con.close(); // -> 여기서 커넥션을 닫으면 안됨
			*/
			
			pstmt.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return affectedRowsCount;
	}

	public BookVO selectWhole(String isbn) {
		BookVO detailBook = null;
		try {
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
			return detailBook;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
