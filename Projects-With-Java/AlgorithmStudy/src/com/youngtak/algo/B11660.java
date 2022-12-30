package com.youngtak.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11660 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int startX = 0, startY = 0, endX = 0, endY = 0, result = 0;
		
		int[][] arr = new int[N][N];
		int[][] subsum = new int[N][N];
		int[] sumarr = new int[N * N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (j > 0) {
					sumarr[i * N + j] = sumarr[i * N + j - 1] + arr[i][j];
				} else { // j == 0
					sumarr[i * N + j] = arr[i][j];
				}
			}
		}
		
		for (int i=0; i<M; i++) {
			result = 0;
			
			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken()) - 1;
			startY = Integer.parseInt(st.nextToken()) - 1;
			endX = Integer.parseInt(st.nextToken()) - 1;
			endY = Integer.parseInt(st.nextToken()) - 1;
			
			for (int j=startX; j<=endX; j++) {
				if (startY == 0) {
					result += subsum[j][endY];
				} else {
					result += subsum[j][endY] - subsum[j][startY - 1];					
				}
			}
			
			System.out.println(result);
		}
		
	}
}
