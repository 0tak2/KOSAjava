package workshop01;

public class Test07 {

	public static void main(String[] args) {
		int i = 5;
		String result = i % 2 == 0 ? "짝수" : "홀수";
		System.out.printf("숫자 %d는 %s입니다.", i, result);
	}
}
