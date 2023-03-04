package org.youngtak2.springboard.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.youngtak2.springboard.member.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public int insert(Member member) {
		int affectedRows = 0;
		try {
			affectedRows = session.insert("org.youngtak2.springboard.member.insert", member);	
		} catch (Exception e) {
			throw e;
		}
		return affectedRows;
	}

	@Override
	public Member login(Member member) {
		return session.selectOne("org.youngtak2.springboard.member.login", member);
	}

	@Override
	public Member select(Member member) {
		return session.selectOne("org.youngtak2.springboard.member.select", member);
	}

}
