package springioc.anno.sample1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml", Food.class);
		
		MyFoodManager obj = context.getBean("myFood", MyFoodManager.class);
		
		System.out.println(obj);
	}
}
