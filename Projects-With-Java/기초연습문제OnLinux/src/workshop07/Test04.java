package workshop07;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Test04 {
	public static void main(String[] args) {
		String strOriginal = "I am second to none";
		
		ArrayList<Integer> spaceIndex = new ArrayList<Integer>();
		for (int i=0; i<strOriginal.length(); i++) {
			if (strOriginal.charAt(i) == ' ')
				spaceIndex.add(i)
		}
		
		for (int index : spaceIndex) {
			
		}
		
		StringTokenizer st = new StringTokenizer(str);
		
		int count = 0;
		while(st.hasMoreTokens()) {
			count++;
			System.out.print(st.nextToken() + " ");
		}
		System.out.println("문자 개수: " + count);
		
		st = new StringTokenizer(str, " ");
		count = 0;
		while(st.hasMoreTokens()) {
			count++;
			System.out.print(st.nextToken() + " ");
		}
		System.out.println("단어 개수: " + count);
	}
}
