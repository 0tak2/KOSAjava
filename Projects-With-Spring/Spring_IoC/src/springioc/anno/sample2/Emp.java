package springioc.anno.sample2;

public class Emp {

	private String name;
	private int age;
	
	public Emp() {
	}

	public Emp(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return name + "\t" + age;
	}
}
