package springioc.sample31;

public class User {
	
	private String userName;
	
	public User() {
		System.out.println("[User] 기본 생성자 호출됨");
	}

	public User(String userName) {
		System.out.println("[User] 생성자 호출됨: " + userName);
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		System.out.println("[User] Setter 호출됨: " + userName);
		this.userName = userName;
	}
}
