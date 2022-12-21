package workshop07;

public class Test08 {
	public static double convert(double d, int n) {
		double tenPow = Math.pow(10, n);
		return Math.round(d * tenPow) / tenPow;
	}
	
	public static void main(String[] args) {
		System.out.println(convert(Math.PI, 1));
		System.out.println(convert(Math.PI, 2));
		System.out.println(convert(Math.PI, 3));
		System.out.println(convert(Math.PI, 4));
	}
}
