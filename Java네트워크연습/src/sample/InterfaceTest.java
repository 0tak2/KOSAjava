package sample;

interface myInterface {
	
	// 인터페이스에는 추상 메서드만 포함할 수 있다.
	// public abstract 생략됨
	void aa();
}

class MyClass implements myInterface {
	
	@Override
	public void aa() {
		
	}
}

public class InterfaceTest {
	public static void main(String[] args) {
		/*
		myInterface a = new myInterface(); // 인터페이스는 바로 인스턴스화할 수 없다.
		//아직 완전히 구현되지 않았기 때문이다.
		*/
		
		MyClass a = new MyClass(); // 이렇게 인터페이스를 구현하는 클래스를 만들어 인스턴스화하거나,
		
		// 이렇게 바로 추상메서드를 오버라이드 할 수 있다.
		// 이렇게 하면 인터페이스를 구현할 때, 매번 클래스를 만들지 않아도 되기 때문에 편리하다.
		myInterface b = new myInterface() {
			@Override
			public void aa() {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
