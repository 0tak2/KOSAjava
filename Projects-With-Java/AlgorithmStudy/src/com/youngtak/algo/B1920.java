package com.youngtak.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class B1920 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		ArrayList<Integer> arr;
		ArrayList<Integer> targetArr;
		int start;
		int end;
		int cursor;
		int center;
		boolean found;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		arr = new ArrayList<Integer>(N);
		
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(arr);
		
		int K = Integer.parseInt(br.readLine());
		targetArr = new ArrayList<Integer>(K);
		
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			targetArr.add(Integer.parseInt(st.nextToken()));
		}
		
		for (int i : targetArr) {
			found = false;
			start = 0;
			end = N-1;
			while(start <= end) {
				cursor = (start + end) / 2;
				center = arr.get(cursor);
				if (i == center) {
					System.out.println(1);
					found = true;
					break;
				} else if (i > center) {
					start = cursor + 1;
				} else {
					end = cursor - 1;
				}
			}
			
			if (!found) {
				System.out.println(0);
			}
		}

	}
}
