package workshop08;

import java.util.ArrayList;
import java.util.HashSet;

public class Test07 {
	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		ArrayList<Integer> list = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		
		for(int i=0; i<size; i++) {
			list.add((int)(Math.random() * 10));
			set.add(list.get(i));
		}
		
		for (int num : list) {
			System.out.print(num + " ");
		}
		System.out.println();
		System.out.println(set);
	}
}
