package board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.vo.Article;
import board.vo.Comment;
import common.mybatis.MyBatisConnectionFactory;
import member.vo.Member;

public class BoardDAO {
	private SqlSession session;
	
	public BoardDAO() {
	}
	
	public BoardDAO(SqlSession session) {
		this.session = session;
	}

	public List<Article> selectAll() {
		// 데이터베이스 처리 - MyBatis 이용
		List<Article> result = session.selectList("myBoard.selectAllArticles");
		session.close();

		return result;
	}

	public Article selectOne(Article param) {
		Article result = session.selectOne("myBoard.selectOneArticle", param);
		session.close();

		return result;
	}

	public int insert(Article param) {
		int affectedRows = session.insert("myBoard.insertArticle", param);
		return affectedRows;
	}

	public List<Comment> selectAllComments(Article param) {
		List<Comment> result = session.selectList("myBoard.selectAllComments", param);
		return result;
	}

	public int insertComment(Comment param) {
		int affectedRows = session.insert("myBoard.insertComment", param);
		return affectedRows;
	}

	public int deleteArticle(Article param) {
		int affectedRows = session.insert("myBoard.deleteArticle", param);
		return affectedRows;
	}

	public int editArticle(Article param) {
		int affectedRows = session.update("myBoard.updateArticle", param);
		return affectedRows;
	}

	public Comment selectOneComment(Comment param) {
		Comment result = session.selectOne("myBoard.selectComment", param);
		return result;
	}

	public int editComment(Comment param) {
		int affectedRows = session.update("myBoard.updateComment", param);
		return affectedRows;
	}

	public int deleteComment(Comment param) {
		int affectedRows = session.delete("myBoard.deleteComment", param);
		return affectedRows;
	}

}
