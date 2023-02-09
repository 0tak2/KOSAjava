package member.dao;

import org.apache.ibatis.session.SqlSession;

import common.mybatis.MyBatisConnectionFactory;
import member.vo.Member;
public class MemberDAO {

	public Member select(Member member) {
		// 데이터베이스 처리 - MyBatis 이용
		SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
		Member result = sqlSession.selectOne("myMember.login", member); // 트랜잭션 처리는 우선 넘긴다
		sqlSession.close();

		return result;
	}

}
