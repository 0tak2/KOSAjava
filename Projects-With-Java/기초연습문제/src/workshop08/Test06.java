package workshop08;

import java.util.HashMap;

public class Test06 {
	public static void main(String[] args) {
		HashMap<Integer, Integer> map1 = new HashMap<>();
		HashMap<Integer, Integer> map2 = new HashMap<>();
		
		for(int i=1; i<=10; i++) {
			map1.put(i, (int)(Math.random() * 10));
			map2.put(i, (int)(Math.random() * 10));
		}
		
		for(int i=1; i<=10; i++) {
			int first = map1.get(i);
			int second = map2.get(11 - i);
			int result;
			
			System.out.print(first + "/" + second + " ");
			
			try {
				result = first / second;
				System.out.print(result);
			} catch (Exception e) {
				System.err.print("0으로는 나눌 수 없음");
			}
			System.out.println();
		}
	}
}
