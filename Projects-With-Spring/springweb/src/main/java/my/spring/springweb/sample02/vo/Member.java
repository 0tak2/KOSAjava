package my.spring.springweb.sample02.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private String name; // 일부러 폼의 name과 같게 필드명을 잡았음.
	private String phone;
	private String id;
	private String password;
	
}
