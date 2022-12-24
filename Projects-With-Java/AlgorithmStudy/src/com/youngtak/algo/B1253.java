package com.youngtak.algo;

import java.util.Arrays;
import java.util.Scanner;

public class B1253 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int start;
		int end;
		int goodCount = 0;
		
		for (int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		

		for (int i=0; i<N; i++) {
			int find = arr[i];
			start = 0;
			end = N - 1;
			while (start < end) {
				int sum = arr[start] + arr[end];
				
				if (sum == find) {
					if (start != i && end != i) {
						goodCount++;
						break;	
					} else if (start == i) {
						start++;
					} else if (end == i) {
						end--;
					}
				} else if (sum < find) {
					start++;
				} else {
					end--;
				}
			}
		}
		System.out.println(goodCount);
	}
}
