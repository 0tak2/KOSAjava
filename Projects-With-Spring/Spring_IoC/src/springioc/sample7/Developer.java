package springioc.sample7;

public class Developer extends Emp {

	private String dept;
	
	public Developer() {
	}

	public Developer(String name, int salary, String dept) {
		super(name, salary);
		this.dept = dept;
	}

	
}
