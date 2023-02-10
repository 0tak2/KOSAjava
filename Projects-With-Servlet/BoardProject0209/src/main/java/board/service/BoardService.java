package board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.dao.BoardDAO;
import board.vo.Article;
import board.vo.Comment;
import common.mybatis.MyBatisConnectionFactory;

public class BoardService {

	public List<Article> getAllArticles() {
		BoardDAO dao = new BoardDAO();
		List<Article> list = dao.selectAll();
		return list;
	}

	public Article getArticle(Article param) {
		BoardDAO dao = new BoardDAO();
		
		Article result = dao.selectOne(param);
		return result;
	}

	public boolean writeArticle(Article param) {
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

	public List<Comment> getAllComments(Article param) {
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

	public boolean deleteArticle(Article param) {
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

	public boolean editArticle(Article param) {
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

	public boolean editComment(Comment param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		
		BoardDAO dao = new BoardDAO(sqlSession);
		int result = dao.editComment(param);
		
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
