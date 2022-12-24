package java2;

import java.util.StringTokenizer;

public class StringTest {

	public static void main(String[] args) {
		String str = "1.22,4.12,5.93,8.71,9.34";
		
		double[] data = new double[5];
		double sum = 0;
		
		StringTokenizer st = new StringTokenizer(str, ",");
		
		for(int i=0; st.hasMoreElements(); i++) {
			data[i] = Double.parseDouble(st.nextToken());
		}
		
		for(double num : data) {
			sum += num;
		}
		System.out.println("합계: " + sum);
		System.out.println("평균: " + sum/data.length);
	}
}
