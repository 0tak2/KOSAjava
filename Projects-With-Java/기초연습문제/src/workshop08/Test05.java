package workshop08;

import java.util.ArrayList;

public class Test05 {
	public static void main(String[] args) {
		ArrayList<Integer> list1 = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		
		for(int i=0; i<10; i++) {
			list1.add((int)(Math.random() * 10));
			list2.add((int)(Math.random() * 10));
		}
		
		for(int i=0; i<10; i++) {
			int first = list1.get(i);
			int second = list2.get(i);
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
