// 작성중
package com.youngtak.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B11003 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Deque<Node> deque = new LinkedList<Node>();
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			int newEl = Integer.parseInt(st.nextToken());
			
			if (deque.getLast().getValue() < newEl) {
				
			}
			
		}
		
		/*
		for (int i=0; i<N; i++) {
			Node firstNode = deque.getFirst();
			Node lastNode = deque.getLast();
			if (lastNode.getIndex() - firstNode.getIndex() == L) {
				
			} else if (lastNode.getIndex() - firstNode.getIndex() < L) {
				
			} else {
				
			}
			deque.addLast(new Node(i, arr[i]));
			
		}
		
		for (int k : D) {
			System.out.print(k + " ");
		}
		*/
	}
}

class Node {
	int index;
	int value;
	
	public Node() {
		
	}

	public Node(int index, int value) {
		super();
		this.index = index;
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public int getValue() {
		return value;
	}
	
	
}
