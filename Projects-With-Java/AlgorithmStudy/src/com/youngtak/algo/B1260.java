package com.youngtak.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1260 {
	static ArrayList<Integer>[] graph;
	static boolean dfsVisited[];
	static boolean bfsVisited[];
	static Queue<Integer> bfsQ = new LinkedList<Integer>();
	static ArrayList<Integer> dfsRoute;
	static ArrayList<Integer> bfsRoute;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		dfsVisited = new boolean[N + 1];
		bfsVisited = new boolean[N + 1];
		dfsRoute = new ArrayList<Integer>(N);
		bfsRoute = new ArrayList<Integer>(N);
		
		for (int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<Integer>();
			dfsVisited[i] = false;
			bfsVisited[i] = false;
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			graph[start].add(end);
			graph[end].add(start);
		}
		
		for (int i=1; i<N+1; i++) {
			Collections.sort(graph[i]);
		}
		
		dfs(V);
		bfs(V, N);
		
		for (int i : dfsRoute) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i : bfsRoute) {
			System.out.print(i + " ");
		}
	}
	
	static void dfs(int V) {
		if (dfsVisited[V]) {
			return;
		}
		
		dfsVisited[V] = true;
		dfsRoute.add(V);
		
		for(int i : graph[V]) {
			dfs(i);
		}
	}
	
	static void bfs(int V, int N) {
		if (bfsVisited[V]) {
			return;
		}
		
		bfsRoute.add(V);
		bfsVisited[V] = true;
		
		while (bfsRoute.size() < N + 1) {
			for (int i : graph[V]) {
				bfsQ.offer(i);
			}
			while(bfsQ.size() > 0) {
				int visiting = bfsQ.poll();
				bfsVisited[visiting] = true;
				bfsRoute.add(visiting);
			}
		}

	}
}
