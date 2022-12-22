package workshop08.list;

import java.util.ArrayList;

public class Test02 {
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		
		MakeList listManager = new MakeList();
		listManager.makeArrayList(N);
		ArrayList<Integer> list = listManager.getList();
		
		for (int i : list) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		System.out.println("평균: " + listManager.getAverage());
	}
}
