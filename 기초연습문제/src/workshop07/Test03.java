package workshop07;

import java.util.StringTokenizer;

public class Test03 {
	public static void main(String[] args) {
		String str = "4,2,3,6,7";
		StringTokenizer st = new StringTokenizer(str, ",");
		int sum = 0;
		while(st.hasMoreTokens()) {
			sum += Integer.parseInt(st.nextToken());
		}
		
		System.out.println(sum);
	}
}
