package workshop06.calc;

public class Test01 {
	public static void main(String[] args) {
		Calc calc = new Calc();
		int input = Integer.parseInt(args[0]);
		if (input < 5 && input > 10) {
			System.out.println("다시 입력해주세요");
			return;
		}
		
		System.out.println(calc.calculate(input));
	}
}
