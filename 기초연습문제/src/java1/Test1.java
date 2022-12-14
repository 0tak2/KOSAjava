package java1;

public class Test1 {
	public static void main(String[] args) {
		int num0 = Integer.parseInt(args[0]);
		int num1 = Integer.parseInt(args[1]);
		int times = num0 * num1;
		
		
		if (times >= 10 && times < 100) {
			System.out.println("두 자리 수 입니다.");
		} else if (times < 10 && times > -1) {
			System.out.println("한 자리 수 입니다.");
		}
	}
}
