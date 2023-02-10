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

}
