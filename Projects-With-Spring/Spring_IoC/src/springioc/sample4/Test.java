package springioc.sample4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml", AbstractDay.class);
		
		((ClassPathXmlApplicationContext)context).close();
	}
}
