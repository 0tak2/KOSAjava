package springioc.anno.sample2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("enginner")
public class Engineer {

	@Autowired
	private Emp emp;
	
	private String dept;
	
	public Engineer() {
	}
	
	@Autowired // 패러미터 dept를 주입해줌. id가 dept인 빈 필요. 필드가 아닌 패러미터의 이름을 따라감. 
	public void my_dept(String dept) {  // Setter 역할을 대신하는 일반 메서드
		this.dept = dept;
		System.out.println("[Engineer] 일반 메서드 my_dept 호출됨");
	}
}
