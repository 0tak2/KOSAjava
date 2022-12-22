package com.youngtak.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B11003_Failed2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int[] D = new int[N];
		int min = (int)Math.pow(10, 9);
		int startInx;
		int endInx;
		int prevMinInx = -1;
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i-L+1<N; i++) {
			if (i > N - 1)
				break;
			
			startInx = i - L + 1;
			endInx = i;
			if (startInx < 0)
				startInx = 0;
			
			if (prevMinInx >= startInx && prevMinInx <= endInx) { // 이전 최솟값 인덱스가 범위 내이면,
				if (arr[prevMinInx] < arr[endInx]) { // 새로 추가된 값만 비교해주면 됨
					D[i] = arr[prevMinInx];
				} else {
					D[i] = arr[endInx];
					prevMinInx = endInx;
				}
			} else {
				min = (int)Math.pow(10, 9);
				int minInx = -1;
				for(int j=startInx; j<=endInx; j++) {
					if (j < 0 || j > N - 1)
						continue;
					
					if (arr[j] < min) {
						min = arr[j];
						minInx = startInx + j;
					}

				}
				D[i] = min;
				prevMinInx = minInx;
			}
			
		}
		
		for (int k : D) {
			System.out.print(k + " ");
		}
	}
}
