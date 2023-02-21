package springioc.sample5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml", User.class);
		
		User user1 = context.getBean("f1", User.class);
		User user2 = context.getBean("f1", User.class);
		User user3 = context.getBean("f1", User.class);
		
		((ClassPathXmlApplicationContext)context).close();
	}
}
