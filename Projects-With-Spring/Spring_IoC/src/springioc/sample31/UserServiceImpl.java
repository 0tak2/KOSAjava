package springioc.sample31;

public class UserServiceImpl implements UserService {

	// 원래 서비스에서는 필드가 들어가면 안되지만 실습을 위해... 실제로는 Stateless하게 구현해야 함
	private User user;
	
	public UserServiceImpl() {
		System.out.println("[UserServiceImpl] 기본 생성자 호출됨");
	}

	public UserServiceImpl(User user) {
		System.out.println("[UserServiceImpl] 생성자 호출됨: " + user);
		this.user = user;
	}

	@Override
	public void addUser(User user) {

		System.out.println("[UserServiceImpl] addUser 메서드 호출됨: " + user.getUserName() + " 추가");
		
		// 비즈니스 로직 처리가 들어가는 부
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
