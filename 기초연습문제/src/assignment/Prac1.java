package assignment;

public class Prac1 {
	public static void main(String[] args) {
		double a = Double.parseDouble(args[0]);
		double b = Double.parseDouble(args[1]);
		
		if (a % b > 1) {
			System.out.println("나머지가 1 보다 크다");
		} else {
			System.out.println("나머지가 1 보다 작거나 같다");
		}
	}
}
