package java1;

public class Test5 {

	public static void main(String[] args) {
		double sum = 0.0, avg = 0.0;
		
		int i = 1;
		while (i < 101) {
			sum += i;
			++i;
		}
		
		avg = sum / 100;
		
		System.out.println("합계: " + sum);
		System.out.println("평균 " + avg);
	}
}
