package com.lgcns.calc;

class Calc {
	public Calc() {
		
	}
	
	public int sum(int a, int b) {
		return a + b;
	}
	
	public int subtract(int a, int b) {
		return a - b;
	}
	
	public int multiply(int a, int b) {
		return a * b;
	}
	
	public int divide(int a, int b) {
		if (b == 0 || b < 0) return 0;
		return a / b;
	}
}

public class CalcTest {

	public static void main(String[] args) {
		Calc calc = new Calc();
		
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		
		System.out.println("합: " + calc.sum(a, b));
		System.out.println("차: " + calc.subtract(a, b));
		System.out.println("곱: " + calc.multiply(a, b));
		System.out.println("나누기: " + calc.divide(a, b));
	}
}
