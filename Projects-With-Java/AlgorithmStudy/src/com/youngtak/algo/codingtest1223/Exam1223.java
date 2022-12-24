package com.youngtak.algo.codingtest1223;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Exam1223 {
	static int ansCount;
	
	static void explore(char[] subGraph) {
		boolean leapedAnother = false;
		for (char current : subGraph) {
			if (current == 'H') {
				if (!leapedAnother) {
					leapedAnother = true;
				} else { // leapedAnother == true
					ansCount++;
					return;
				}
			}
			
			if (current == 'Y') {
				return;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T;
		int N;
		char[][] graph;
		char[] subGraph;
		int xPos_a = 0;
		int xPos_b = 0;
		ansCount = 0;
		
		System.setIn(new FileInputStream("src/CodingExam/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			graph = new char[N][N];
			
			// 배열 채우기
			for (int a=0; a<N; a++) {
				st = new StringTokenizer(br.readLine());
				int b = 0;
				while(st.hasMoreTokens()) {
					graph[a][b] = st.nextToken().charAt(0);
					if (graph[a][b] == 'X') {
						xPos_a = a;
						xPos_b = b;
					}
					b++;
				}
			}
			
			// 오른쪽
			subGraph = new char[N];
			for (int i=1; i < subGraph.length; i++) {
				try {
					subGraph[i] = graph[xPos_a][xPos_b + i];
				} catch (Exception e) {
					subGraph[i] = 'N';
				}
			}
			explore(subGraph);
			
			// 왼쪽
			subGraph = new char[N];
			for (int i=1; xPos_b-i>=0; i++) {
				subGraph[i] = graph[xPos_a][xPos_b - i];
			}
			explore(subGraph);
			
			// 아래
			subGraph = new char[N];
			for (int i=1; i < subGraph.length; i++) {
				try {
					subGraph[i] = graph[xPos_a + i][xPos_b];
				} catch (Exception e) {
					subGraph[i] = 'N';
				}
			}
			explore(subGraph);
			
			// 위
			subGraph = new char[N];
			for (int i=1; xPos_a-i>=0; i++) {
				subGraph[i] = graph[xPos_a - i][xPos_b];
			}
			explore(subGraph);
			
			System.out.println("#" + (3-T) + " " + ansCount);
			ansCount = 0;
		}
	}
}
