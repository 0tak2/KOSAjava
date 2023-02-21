package springioc.sample1;

public class MessageBeanImpl implements MessageBean {

	private String fruit; // 무상태 아닌지 따져 설계하는 것은 잠시 넘기기
	private int cost;
	
	public MessageBeanImpl() {
		System.out.println("MessageBeanImple 기본 생성자 호출");
	}

	public MessageBeanImpl(String fruit) {
		this.fruit = fruit;
		System.out.println("MessageBeanImple 생성자 호출 => " + fruit);
	}

	public MessageBeanImpl(String fruit, int cost) {
		this.fruit = fruit;
		this.cost = cost;
		System.out.println("MessageBeanImple 생성자 호출 => " + fruit + " / " + cost);
	}

	public void setCost(int cost) {
		System.out.println("MessageBeanImple 세터 호출");
		this.cost = cost;
	}

	@Override
	public void sayHello() {
		System.out.println(fruit + ": " + cost);
	}
}
