package org.youngtak2.springboard.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youngtak2.springboard.member.dao.MemberDao;
import org.youngtak2.springboard.member.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;
	
	@Override
	public boolean registerNewAccount(Member newMember) {
		int affectedRows = dao.insert(newMember);
		if (affectedRows == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Member login(Member member) {
		return dao.login(member);
	}

}
