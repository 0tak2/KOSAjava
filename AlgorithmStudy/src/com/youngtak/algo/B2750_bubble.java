package com.youngtak.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2750_bubble {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		for (int i=0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int sorted = 0;
		while(sorted < arr.length) {
			for (int i=0; i < arr.length - 1 - sorted; i++) {
				if (arr[i] > arr[i+1]) {
					int temp = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = temp;
				}
			}
			sorted++;
		}
		
		for (int num : arr) {
			System.out.println(num);
		}
	}
}
