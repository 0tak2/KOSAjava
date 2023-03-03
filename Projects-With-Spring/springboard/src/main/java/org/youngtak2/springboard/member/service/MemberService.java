package org.youngtak2.springboard.member.service;

import org.youngtak2.springboard.member.vo.Member;

public interface MemberService {
	public boolean registerNewAccount(Member newMember);
	public Member login(Member member);
}
