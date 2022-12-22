package com.youngtak.algo;

import java.util.Scanner;

public class B11659_Failed {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int T = sc.nextInt();
		int[] arr = new int[100000];
		
		for (int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		for (int i=0; i<T; i++) {
			int start = sc.nextInt() - 1;
			int end = sc.nextInt() - 1;
			int sum = 0;
			
			for (int j=start; j<=end; j++) {
				sum += arr[j];
			}
			System.out.println(sum);
		}
	}
}
