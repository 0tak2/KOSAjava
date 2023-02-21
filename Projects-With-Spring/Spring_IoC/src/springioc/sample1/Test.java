package springioc.sample1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		System.out.println("IoC/DI 컨테이너 생성 시작");
		
		// ApplicationContext
		ApplicationContext context =  // XML 설정 불러오기
				new ClassPathXmlApplicationContext("applicationContext.xml",
						MessageBean.class); // 파일 이름과 해당 파일의 위치 지정. (클래스가 있는 디렉토리에서 탐색)
		
		System.out.println("IoC/DI 컨테이너 생성 완료");
		
		MessageBean myBean = context.getBean("messageBean", MessageBean.class);
		MessageBean myBean2 = context.getBean("messageBean", MessageBean.class);
		// 싱글톤의 경우 이미 이 시점에 컨텍스트 안에 있는 Bean을 찾아 리턴
		// 포로토탸입의 경우 이 시점에 Bean 객체가 생성됨.
		
		myBean.sayHello();
		
		// 또한 두 객체 동일성 여부 비교
		System.out.println(myBean);
		System.out.println(myBean2);
		
		((ClassPathXmlApplicationContext) context).close(); // ApplicationContext 닫
	}
}
