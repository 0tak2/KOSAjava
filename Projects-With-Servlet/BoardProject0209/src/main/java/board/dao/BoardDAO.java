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
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		List<Article> result = sqlSession.selectList("myBoard.allArticles"); // 트랜잭션 처리는 우선 넘긴다
		sqlSession.close();

		return result;
	}

	public Article selectOne(Article param) {
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		Article result = sqlSession.selectOne("myBoard.oneArticle", param); // 트랜잭션 처리는 우선 넘긴다
		sqlSession.close();

		return result;
	}

	public int insert(Article param) {
		int affectedRows = session.insert("myBoard.insert", param); // 트랜잭션 처리는 우선 넘긴다
		return affectedRows;
	}

	public List<Comment> selectAllComments(Article param) {
		List<Comment> result = session.selectList("myBoard.allComments", param);
		return result;
	}

}
