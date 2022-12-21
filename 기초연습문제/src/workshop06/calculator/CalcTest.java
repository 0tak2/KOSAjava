package workshop06.calculator;

public class CalcTest {
	public static void main(String[] args) {
		int a = Integer.parseInt(args[0]);
		char op = args[1].charAt(0);
		int b = Integer.parseInt(args[2]);
		
		Calculator calc = new Calculator();
		
		if (op == '+')
			System.out.println(calc.plus(a, b));
		else if (op == '-')
			System.out.println(calc.minus(a, b));
		else if (op == 'x')
			System.out.println(calc.multiplication(a, b));
		else if (op == '/')
			try {
				System.out.println(calc.divide(a,b));
			} catch (Exception e) {
				System.err.println("예외가 발생하였습니다. " + e);
			}
	}
}
