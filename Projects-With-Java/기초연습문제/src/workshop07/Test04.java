package workshop07;

import java.util.StringTokenizer;

public class Test04 {
	public static void main(String[] args) {
		String str1 = "I am second to none";
		String str2 = str1.replaceAll(" ", "");
		StringBuffer sb = new StringBuffer(str2.length() * 2);
		
		for (int i=0; i < str2.length(); i++) {
			sb.append(str2.charAt(i));
			sb.append(" ");
		}
		
		StringTokenizer st =
				new StringTokenizer(sb.toString(), " ");
		
		int count = 0;
		while(st.hasMoreTokens()) {
			count++;
			System.out.print(st.nextToken() + " ");
		}
		System.out.println("문자 개수: " + count);
		
		st = new StringTokenizer(str1, " ");
		count = 0;
		while(st.hasMoreTokens()) {
			count++;
			System.out.print(st.nextToken() + " ");
		}
		System.out.println("단어 개수: " + count);
	}
}
