package workshop08;

import java.util.HashSet;

public class Test01 {
	public static void main(String[] args) {
		String str = args[0];
		HashSet<Character> set = new HashSet<>();
		
		
		for(int i=0; i < str.length(); i++) {
			set.add(str.charAt(i));
		}
		
		System.out.println(set);
	}
}
