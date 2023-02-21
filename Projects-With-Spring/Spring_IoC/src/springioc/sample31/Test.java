package springioc.sample31;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		// ApplicationContext 객체 생성
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml", User.class);
		
		UserService service = context.getBean("userService", UserService.class);
		User user = context.getBean("obj1", User.class); // 실제 프로젝트에서는 VO를 빈으로 등록하지 않는다.
		service.addUser(user);

		((ClassPathXmlApplicationContext)context).close();
	}
}
