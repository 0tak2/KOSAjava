package java1;

public class Test2 {
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		
		if (num % 2 == 0) {
			System.out.println("2의 배수입니다.");
		} else {
			System.out.println("2의 배수가 아닙니다.");
		}
	}
}
