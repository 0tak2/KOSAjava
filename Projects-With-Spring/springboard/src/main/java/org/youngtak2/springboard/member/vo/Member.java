package org.youngtak2.springboard.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private String memberId;
	private String memberName;
	private String memberPw;
}
