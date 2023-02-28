package sample07.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {

	private String memberId;
	private String memberName;
	private String memberAddr;
	private String memberHobby;
	private String memberContent;
}
