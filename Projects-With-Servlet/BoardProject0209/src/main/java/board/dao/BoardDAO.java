package board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.vo.ArticleExtended;
import board.vo.Comment;
import board.vo.Like;

public class BoardDAO {
	private SqlSession session;
	
	public BoardDAO() {
	}
	
	public BoardDAO(SqlSession session) {
		this.session = session;
	}

	public List<ArticleExtended> selectAll() {
		// 데이터베이스 처리 - MyBatis 이용
		List<ArticleExtended> result = session.selectList("myBoard.selectAllArticles");
		session.close();

		return result;
	}

	public ArticleExtended selectOne(ArticleExtended param) {
		ArticleExtended result = session.selectOne("myBoard.selectOneArticle", param);
		session.close();

		return result;
	}

	public int insert(ArticleExtended param) {
		int affectedRows = session.insert("myBoard.insertArticle", param);
		return affectedRows;
	}

	public List<Comment> selectAllComments(ArticleExtended param) {
		List<Comment> result = session.selectList("myBoard.selectAllComments", param);
		return result;
	}

	public int insertComment(Comment param) {
		int affectedRows = session.insert("myBoard.insertComment", param);
		return affectedRows;
	}

	public int deleteArticle(ArticleExtended param) {
		int affectedRows = session.insert("myBoard.deleteArticle", param);
		return affectedRows;
	}

	public int editArticle(ArticleExtended param) {
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

	public Like selectLike(Like param) {
		Like result = session.selectOne("myBoard.selectLike", param);
		return result;
	}

	public int insertLike(Like param) {
		int affectedRows = session.insert("myBoard.insertLike", param);
		return affectedRows;
	}

	public int deleteLike(Like param) {
		int affectedRows = session.delete("myBoard.deleteLike", param);
		return affectedRows;
	}

}
