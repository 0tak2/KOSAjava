package org.youngtak2.springboard.member.dao;

import org.springframework.stereotype.Repository;
import org.youngtak2.springboard.member.vo.Member;

public interface MemberDao {

	public Member login(Member member);
	
	public Member select(Member member);
	
	public int insert(Member member);
}
