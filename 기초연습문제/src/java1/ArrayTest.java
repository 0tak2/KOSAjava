package java1;

public class ArrayTest {

	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int result = 0;
		
		for(int i=0; i<10; ++i) {
			if (array[i] % 2 == 0) continue;
			result += array[i];
			System.out.println(array[i]);
		}
		
		System.out.println("합계: " + result);
		System.out.println("평균: " + (double)result / 10);
	}
}
