package springioc.sample7;

public class Engineer {

	private Emp emp; // 상속받지 않기 위해 필드로 잡는다.
	private String dept;
	
	public Engineer() {
	}

	public Engineer(Emp emp, String dept) {
		this.emp = emp;
		this.dept = dept;
	}
}
