package board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.dao.BoardDAO;
import board.vo.ArticleExtended;
import board.vo.Comment;
import board.vo.Like;
import common.mybatis.MyBatisConnectionFactory;

public class BoardService {

	public List<ArticleExtended> getAllArticles() {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		
		List<ArticleExtended> list = dao.selectAll();
		return list;
	}

	public ArticleExtended getArticle(ArticleExtended param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		
		ArticleExtended result = dao.selectOne(param);
		return result;
	}

	public boolean writeArticle(ArticleExtended param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		int result = dao.insert(param);
		
		if (result == 1) {
			sqlSession.commit();
			sqlSession.close();
			return true;
		} else {
			sqlSession.rollback();
			sqlSession.close();
			return false;
		}
	}

	public List<Comment> getAllComments(ArticleExtended param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		List<Comment> result = dao.selectAllComments(param);
		
		return result;
	}

	public boolean writeComment(Comment param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		int result = dao.insertComment(param);
		
		if (result == 1) {
			sqlSession.commit();
			sqlSession.close();
			return true;
		} else {
			sqlSession.rollback();
			sqlSession.close();
			return false;
		}
	}

	public boolean deleteArticle(ArticleExtended param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		int result = dao.deleteArticle(param);
		
		if (result == 1) {
			sqlSession.commit();
			sqlSession.close();
			return true;
		} else {
			sqlSession.rollback();
			sqlSession.close();
			return false;
		}
	}

	public boolean editArticle(ArticleExtended param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		int result = dao.editArticle(param);
		
		if (result == 1) {
			sqlSession.commit();
			sqlSession.close();
			return true;
		} else {
			sqlSession.rollback();
			sqlSession.close();
			return false;
		}
	}

	public Comment getOneComment(Comment param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		Comment result = dao.selectOneComment(param);
		
		return result;
	}

	public Comment editComment(Comment param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		int result = dao.editComment(param);
		
		Comment newComment = dao.selectOneComment(param);
		
		if (result == 1 && newComment != null) {
			sqlSession.commit();
			sqlSession.close();
			return newComment;
		} else {
			sqlSession.rollback();
			sqlSession.close();
			return null;
		}
	}

	public boolean deleteComment(Comment param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		int result = dao.deleteComment(param);
		
		if (result == 1) {
			sqlSession.commit();
			sqlSession.close();
			return true;
		} else {
			sqlSession.rollback();
			sqlSession.close();
			return false;
		}
	}

	public Like getLike(Like param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		Like result = dao.selectLike(param);
		
		return result;
	}

	public boolean setLike(Like param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		int result = dao.insertLike(param);
		
		if (result == 1) {
			sqlSession.commit();
			sqlSession.close();
			return true;
		} else {
			sqlSession.rollback();
			sqlSession.close();
			return false;
		}
	}

	public boolean unSetLike(Like param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		int result = dao.deleteLike(param);
		
		if (result == 1) {
			sqlSession.commit();
			sqlSession.close();
			return true;
		} else {
			sqlSession.rollback();
			sqlSession.close();
			return false;
		}
	}

}
