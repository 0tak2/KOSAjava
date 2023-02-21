package step8;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import step8.dao.UserDao;
import step8.vo.User;

public class UserDaoTest {

	public static void main(String[] args) throws Exception {

		System.out.println("[STEP8]");
		
		// 1. 사용자 VO 생성
		User user = new User("hong", "1234", "홍길동");
		
		// 2. DAO 생성
//		ConnectionMaker connectionMaker = new SimpleMakeConnection(); // 의존성 제거를 위해 밖에서 생성
//		UserDao dao = new UserDao(connectionMaker);
//		UserDao dao = new DaoFactory().userDao();

		// ApplicationContext 생성 후 UserDao 객체 요청
		ApplicationContext context =
				new AnnotationConfigApplicationContext(DaoFactory.class); // 어노테이션을 이용해 설정을 잡는 ApplicationContext

		// UserDao dao = (UserDao) context.getBean("getUserDao");
		UserDao dao = context.getBean("userDao", UserDao.class); // 클래스의 타입을 넘겨 캐스팅 생략 가능

		// 3. 사용자 입력
		dao.insert(user);
		System.out.println("사용자 등록 성공");

		// 4. 사용자 조회
		User result = dao.select("hong");
		System.out.println(result.getName());

	}
}
