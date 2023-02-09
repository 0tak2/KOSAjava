package member.vo;

public class Member {

	private String memberId;
	private String memberName;
	private String memberPw;
	
	public Member() {
	}

	public Member(String memberId, String memberName, String memberPw) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPw = memberPw;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	
}
