package springioc.sample5;

public class User {

	private String name;
	private String birth;
	
	public User() {
	}

	public User(String name, String birth) {
		super();
		this.name = name;
		this.birth = birth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	@Override
	public String toString() {
		return name + "의 생일은: " + birth;
	}
}
