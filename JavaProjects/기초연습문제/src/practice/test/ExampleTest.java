package practice.test;

public class ExampleTest {
	public static void main(String[] args) {
		// 터미널에서 실행한다면,
		// java ExampleTest arg1 arg2
		// 와 같이 인자를 받을 수 있다.
		// 해당 인자들은 main 메소드의 args String 배열로 들어가게 된다.
		
		System.out.println(args[0] + ", " + args[1]);
	}
}
