package springioc.sample2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml", Bar.class);
		
		((ClassPathXmlApplicationContext)context).close();
	}
}
