package springioc.sample2;

public class Foo {

	public Foo() {
		System.out.println("[Foo] 기본 생성자 호출");
	}
	
	public Foo(String str) {
		System.out.println("[Foo] 생성자 호출: " + str);
	}
	
	public Foo(String str, int num) {
		System.out.println("[Foo] 생성자 호출: " + str + ", " + num);
	}
	
	public Foo(Bar bar) { // 디펜던시 인젝션 같은 상황을 가정
		System.out.println("[Foo] 생성자 호출: " + bar);
	}
}
