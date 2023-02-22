package springioc.sample6;

public class ConsoleOutput implements Output {

	public ConsoleOutput() {
		System.out.println("[ConsoleOutput] 기본 생성자 호출됨");
	}
	
	@Override
	public void print(String message) throws Exception {
		System.out.println("[ConsoleOutput] print 메서드 호출됨: " + message);
		System.out.println(message);
	}
}
