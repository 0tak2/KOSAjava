package com.youngtak.algo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B11724_ans {
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N + 1];
		for (int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		visited = new boolean[N + 1];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			graph[S].add(E);
			graph[E].add(S);
		}
		
		for (int i=1; i<N+1; i++) {
			if (!visited[i]) {
				dfs(i);
				count++;
			}
		}
		System.out.println(count);
	}
	
	static void dfs(int i) {
		if (visited[i]) {
			return;
		}
		
		visited[i] = true;
		
		for (int current : graph[i]) {
			if (!visited[current]) {
				dfs(current);
			}
		}
	}
}
