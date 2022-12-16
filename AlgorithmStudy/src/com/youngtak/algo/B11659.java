package com.youngtak.algo;

import java.util.Scanner;

public class B11659 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int T = sc.nextInt();
		int[] arr = new int[100000];
		int[] sum = new int[100000];
		
		for (int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
			if (i == 0)
				sum[i] = arr[i];
			else
				sum[i] = sum[i-1] + arr[i];
		}
		
		for (int i=0; i<T; i++) {
			int start = sc.nextInt() - 1;
			int end = sc.nextInt() - 1;
			if (start == 0)
				System.out.println(sum[end]);
			else
				System.out.println(sum[end] - sum[start-1]);
		}
	}
}
