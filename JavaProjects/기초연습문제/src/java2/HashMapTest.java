package java2;

import java.util.HashMap;

public class HashMapTest {

	public static void main(String[] args) {
		HashMap<Integer, Integer> map =
				new HashMap<Integer, Integer>();
		
		double sum = 0;
		
		for (int i=0; i<10; ++i) {
			map.put(i, (int)((Math.random()*100)+1));
		}
		
		for (int i=0; i<10; ++i) {
			int num = map.get(i);
			System.out.print(num + " ");
			sum += (double) num;
		}
		
		System.out.println();
		System.out.printf("합계: %.2f%n", sum);
		System.out.printf("평균: %.2f%n", sum/10);
	}
}
