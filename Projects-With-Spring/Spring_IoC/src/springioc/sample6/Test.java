package springioc.sample6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		System.out.println("컨텍스트 생성 시작");
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml", Output.class);
		
		System.out.println("컨텍스트 생성 완료\n");
		
		MessageBean bean1 = context.getBean("myBean", MessageBean.class);
		MessageBean bean2 = context.getBean("anotherBean", MessageBean.class);
		
		bean1.sayHello(); // 필드의 output에 어떤 클래스가 주입되는지에 따라 출력 디렉션이 정해짐
		bean2.sayHello();
		
		((ClassPathXmlApplicationContext)context).close();
	}
}
