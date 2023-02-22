package springioc.sample9;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml", DataBean.class);
		
		TestBaen bean = context.getBean("myObj", TestBaen.class);
		
		System.out.println(bean.getData1());
		System.out.println(bean.getData2());
		
		((ClassPathXmlApplicationContext)context).close();
	}
}
