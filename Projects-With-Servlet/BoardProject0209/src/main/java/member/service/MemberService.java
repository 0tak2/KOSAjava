package member.service;

import member.dao.MemberDAO;
import member.vo.Member;

public class MemberService {

	public Member login(Member member) {
		// 로그인 처리
		// 데이터베이스 처리는 DAO에서

		MemberDAO dao = new MemberDAO();
		Member result = dao.select(member);
		return result;
	}

}
