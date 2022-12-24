package java2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(10);
		
		for(int i=0; i<10; ++i) {
			int randomNum = (int) ((Math.random()*10)+1);
			list.add(randomNum);
			System.out.print(randomNum + " ");
		}
		System.out.println();
		
		Collections.sort(list);
		
		for(int num : list) {
			System.out.print(num + " ");
		}
	}
}
