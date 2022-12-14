package com.youngtak.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class B11724_Mine {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(N+1);
		for(int i=0; i < N+1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		boolean[] visited = new boolean[N+1];
		Stack<Integer> stack = new Stack<>();
		int visitedCount = 0;
		int connCount = 1;
		
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(end);
			graph.get(end).add(start);
		}
		
		stack.push(1);
		while(true) {
			while(stack.size() > 0) {
			 	int current = stack.pop();
			 	if (!visited[current]) {
			 		ArrayList<Integer> dests = graph.get(current);
				 	
				 	for (int dest : dests) {
				 		stack.push(dest);
				 	}
				 	
				 	visited[current] = true;
				 	visitedCount++;
			 	}
			}
			
			if (visitedCount >= N) {
				break;
			} else {
				connCount++;
				for (int i=1; i < visited.length; i++) {
					if(!visited[i]) {
						stack.push(i);
						break;
					}
				}
			}
		}
		
		System.out.println(connCount);
	}
}
