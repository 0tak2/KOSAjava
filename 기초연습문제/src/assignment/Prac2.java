package assignment;

public class Prac2 {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		
		int result = 1;
		System.out.print("1");
		for (int i=2; i<=n; i++) {
			result *= i;
			System.out.print("*" + i);
		}
		System.out.print(" = " + result);
	}
}
