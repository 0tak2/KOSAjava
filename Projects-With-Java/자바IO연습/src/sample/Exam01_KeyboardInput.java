package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exam01_KeyboardInput {
	public static void main(String[] args) {
		System.out.println("키보드로 한 줄을 입력하세요!");
		
		// 키보드로부터 입력을 받으려면, 데이터 연결 통로(Stream)이 필요하다
		//		System.in이 기본적으로 제공되지만, 사용이 너무 불편함.
		//		우리는 문자열 데이터를 받고자 하니 Reader을 하나 만들어야 함.
		// new InputStreamReader(System.in);
		// 스트림을 결합하여 조금 더 편리한 문자 기반 스트림을 열 수 있지만,
		// 그럼에도 불편하므로, BufferedReader을 사용할 것임. (내부 버퍼가 있어 더 편리)
		// 앞으로 입력은 BufferedReader를 사용하겠음.
		BufferedReader br =
				new BufferedReader(new InputStreamReader(System.in));
		
		try { // 입력도 에러의 여지가 많기 때문에 예외처리가 강제화되어 있음.
			String s = br.readLine(); // 엔터(\n)를 치는 순간 개행문자까지 포함하여 한 라인으로 읽음.
			System.out.println("입력받은 데이터는: " + s);
		} catch (IOException e) {
		}
	}
}
