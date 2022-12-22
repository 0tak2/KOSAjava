package com.youngtak.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11003_Failed1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int[] D = new int[N];
		int min = (int)Math.pow(10, 9);
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i-L+1<N; i++) {
			if (i > N - 1)
				break;
			
			int startInx = i - L + 1;
			int endInx = i;
			
			min = (int)Math.pow(10, 9);
			for(int j=startInx; j<=endInx; j++) {
				if (j < 0 || j > N - 1)
					continue;
				
				if (arr[j] < min)
					min = arr[j];
			}
			D[i] = min;
		}
		
		for (int k : D) {
			System.out.print(k + " ");
		}
	}
}
