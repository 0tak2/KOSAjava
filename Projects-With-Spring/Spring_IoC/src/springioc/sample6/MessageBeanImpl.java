package springioc.sample6;

public class MessageBeanImpl implements MessageBean {

	private String name;
	private String phone;
	private Output output; // 전략을 받을 인터페이스를 필드로 갖는다.
	
	public MessageBeanImpl() {
		System.out.println("[MessageBeanImpl] 기본 생성자 호출됨");
	}

	public MessageBeanImpl(String name) { // 여러 상황을 연습해보기 위해 이름만 생성자로 받는다
		this.name = name;
		System.out.println("[MessageBeanImpl]  생성자 호출됨: " + name);
	}


	// 나머지 필드는 세터로 입력받을 것이다
	
	public void setPhone(String phone) {
		this.phone = phone;
		System.out.println("[MessageBeanImpl] 세터 setPhone 호출됨: " + phone);
	}

	public void setOutput(Output output) {
		this.output = output;
		System.out.println("[MessageBeanImpl] 세터 setOutput 호출됨: " + output);
	}

	@Override
	public void sayHello() {
		System.out.println("[MessageBeanImpl] sayHello 메서드 호출");
		
		String msg = name + "(" + phone +")님 안녕하세요.";
		
		try {
			output.print(msg);
			System.out.println("[MessageBeanImpl] sayHello 메서드 종료됨");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
