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
			result = session.selectOne("org.youngtak2.springboard.board.selectArticleCounts");			
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
			result = session.selectList("org.youngtak2.springboard.board.selectAllArticles");
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
			result = session.selectList("org.youngtak2.springboard.board.selectArticles", param);			
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public ArticleExtended selectOneArticle(Article articleParam) {
		ArticleExtended result = null;
		try {
			result = session.selectOne("org.youngtak2.springboard.board.selectOneArticle", articleParam);			
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int insertArticle(Article newArticle) {
		int affectedRows = 0;
		try {
			affectedRows = session.insert("org.youngtak2.springboard.board.insertArticle", newArticle);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public int editArticle(Article param) {
		int affectedRows = 0;
		try {
			affectedRows = session.update("org.youngtak2.springboard.board.updateArticle", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public int deleteArticle(Article param) {
		int affectedRows = 0;
		try {
			affectedRows = session.delete("org.youngtak2.springboard.board.deleteArticle", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public List<Comment> selectAllComments(Article param) {
		List<Comment> result = null;
		try {
			result = session.selectList("org.youngtak2.springboard.board.selectAllComments", param);			
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public Comment selectOneComment(Comment param) {
		Comment result = null;
		try {
			result = session.selectOne("org.youngtak2.springboard.board.selectComment", param);			
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int insertComment(Comment param) {
		int affectedRows = 0;
		try {
			affectedRows = session.insert("org.youngtak2.springboard.board.insertComment", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public int editComment(Comment param) {
		int affectedRows = 0;
		try {
			affectedRows = session.update("org.youngtak2.springboard.board.updateComment", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public int deleteComment(Comment param) {
		int affectedRows = 0;
		try {
			affectedRows = session.delete("org.youngtak2.springboard.board.deleteComment", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public Like selectLike(Like param) {
		Like result = null;
		try {
			result = session.selectOne("org.youngtak2.springboard.board.selectLike", param);		
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int insertLike(Like param) {
		int affectedRows = 0;
		try {
			affectedRows = session.insert("org.youngtak2.springboard.board.insertLike", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public int deleteLike(Like param) {
		int affectedRows = 0;
		try {
			affectedRows = session.delete("org.youngtak2.springboard.board.deleteLike", param);
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

}
