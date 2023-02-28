package my.spring.springweb.sample07.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	// 커맨드 객체로 쓸 것이기 때문에
	// 클라이언트 측 폼 name과 필드명이 같아야 함

	private int studentNumber;
	private String studentName;
	private String studentDept;
}
