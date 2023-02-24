package my.spring.springweb.sample02.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
@AllArgsConstructor
@Data
public class User {

	private String userName;
	private int userAge;
	private String userDept;
	private String userAddress;
}
