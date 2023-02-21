package step5;

import step5.dao.UserDao;
import step5.vo.User;

public class UserDaoTest {

	public static void main(String[] args) throws Exception {
		
		System.out.println("[STEP5]");
		
		// 1. 사용자 VO 생성
		User user = new User("hong", "1234", "홍길동");
		
		// 2. DAO 생성
		UserDao dao = new UserDao();
		
		// 3. 사용자 입력
		dao.insert(user);
		System.out.println("사용자 등록 성공");
		
		// 4. 사용자 조회
		User result = dao.select("hong");
		System.out.println(result.getName());
		
	}
}
