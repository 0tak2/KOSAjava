package springioc.homework1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml", Friend.class);
		
		BestFriend bf1 = context.getBean("bestFriend", BestFriend.class);
		BestFriend bf2 = context.getBean("bestFriend", BestFriend.class);
		
		System.out.println(bf1 == bf2);
		
		((ClassPathXmlApplicationContext)context).close();
	}
}
