package org.youngtak2.springboard.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.youngtak2.springboard.board.vo.Article;
import org.youngtak2.springboard.board.vo.ArticleExtended;
import org.youngtak2.springboard.board.vo.Comment;
import org.youngtak2.springboard.board.vo.Like;
import org.youngtak2.springboard.common.vo.BasicSelect;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public BasicSelect selectArticleCounts() {
		BasicSelect result = null;
		try {
			result = session.selectOne("myBoard.selectArticleCounts");			
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public List<ArticleExtended> selectAllArticles() {
		// 데이터베이스 처리 - MyBatis 이용
		List<ArticleExtended> result = null;
		try {
			result = session.selectList("myBoard.selectAllArticles");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public List<ArticleExtended> selectArticles(BasicSelect param) {
		// 데이터베이스 처리 - MyBatis 이용
		List<ArticleExtended> result = null;
		try {
			result = session.selectList("myBoard.selectArticles", param);			
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public ArticleExtended selectOneArticle(Article articleParam) {
		ArticleExtended result = null;
		try {
			result = session.selectOne("myBoard.selectOneArticle", articleParam);			
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int insertArticle(Article newArticle) {
		int affectedRows = 0;
		try {
			affectedRows = session.insert("myBoard.insertArticle", newArticle);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public int editArticle(Article param) {
		int affectedRows = 0;
		try {
			affectedRows = session.update("myBoard.updateArticle", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public int deleteArticle(Article param) {
		int affectedRows = 0;
		try {
			affectedRows = session.delete("myBoard.deleteArticle", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public List<Comment> selectAllComments(Article param) {
		List<Comment> result = null;
		try {
			result = session.selectList("myBoard.selectAllComments", param);			
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public Comment selectOneComment(Comment param) {
		Comment result = null;
		try {
			result = session.selectOne("myBoard.selectComment", param);			
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int insertComment(Comment param) {
		int affectedRows = 0;
		try {
			affectedRows = session.insert("myBoard.insertComment", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public int editComment(Comment param) {
		int affectedRows = 0;
		try {
			affectedRows = session.update("myBoard.updateComment", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public int deleteComment(Comment param) {
		int affectedRows = 0;
		try {
			affectedRows = session.delete("myBoard.deleteComment", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public Like selectLike(Like param) {
		Like result = null;
		try {
			result = session.selectOne("myBoard.selectLike", param);		
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int insertLike(Like param) {
		int affectedRows = 0;
		try {
			affectedRows = session.insert("myBoard.insertLike", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public int deleteLike(Like param) {
		int affectedRows = 0;
		try {
			affectedRows = session.delete("myBoard.deleteLike", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

}
