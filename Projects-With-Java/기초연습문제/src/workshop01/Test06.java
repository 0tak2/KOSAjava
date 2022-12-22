package workshop01;

public class Test06 {

	public static void main(String[] args) {
		byte a = 10;
		byte b = 20;
		byte c = (byte)(a + b);
		char ch = 'A';
		ch = (char)(ch + 2);
		float f = 1.5f;
		long l = 27000000000l;
		float f2 = 0.1f;
		double d = 0.1;
		boolean result = (float)d == f2;
		System.out.println("c = " + c);
		System.out.println("ch = " + ch);
		System.out.println("f = " + f);
		System.out.println("l = " + l);
		System.out.println("result = " + result);
	}
}
