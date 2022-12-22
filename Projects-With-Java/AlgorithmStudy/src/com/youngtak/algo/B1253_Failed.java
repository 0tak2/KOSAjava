package com.youngtak.algo;

import java.util.Scanner;

public class B1253_Failed {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[2000];
		int lastIndex = 0;
		int N = sc.nextInt();
		int goodCount = 0;
		
		for (int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
			lastIndex = i;
		}
		
		for (int i=0; i<=lastIndex; i++) {
			int num = arr[i];
			boolean isGood = false;
			
			loop: for (int j=0; j<=lastIndex; j++) {
				int otherA = arr[j];
				int otherB = num - otherA;
				
				for (int k=0; k<=lastIndex; k++) {
					if (arr[k] == otherB) {
						if (k == j) continue;
						isGood = true;
						break loop;
					}
				}
			}
			
			if(isGood)
				goodCount++;
		}
		
		System.out.println(goodCount);
	}
}
