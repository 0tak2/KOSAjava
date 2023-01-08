package com.youngtak.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1920_Failed {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int cnt = (int)Math.pow(2, 31);
		int[] negativeArr = new int[cnt];
		int[] positiveArr = new int[cnt];
		int zero = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			int input = Integer.parseInt(st.nextToken());
			if (input < 0) {
				input *= -1;
				negativeArr[input] = 1;
			} else if (input > 0) {
				positiveArr[input] = 1;
			} else {
				zero = 1;
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			int input = Integer.parseInt(st.nextToken());
			if (input < 0) {
				input *= -1;
				System.out.println(negativeArr[input]);
			} else if (input > 0) {
				System.out.println(positiveArr[input]);
			} else {
				System.out.println(zero);
			}
		}
	}
}
