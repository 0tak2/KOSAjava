package workshop06.calculator;

public class Calculator {
	public double plus(int a, int b) {
		return a + b;
	}
	
	public double minus(int a, int b) {
		return a - b;
	}
	
	public double multiplication(int a, int b) {
		return a * b;
	}
	
	public double divide(int a, int b) throws Exception {
		if (b == 0) {
			throw new Exception("0으로는 나눌 수 없습니다");
		}
		return a / b;
	}
}
