package workshop04;

public class Test03 {

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		int sum = 0;
		
		for (int i=num; i<=10; i++) {
			if (i % 3 == 0 || i % 5 == 0)
				continue;
			sum += i;

			if(i+1 < 10 && (i+1) % 3 != 0 && (i+1) % 5 != 0) {
				System.out.print(i + "+");
			} else {
				System.out.print(i);
			}
		}
		
		System.out.println();
		System.out.println("결과: " + sum);
	}
}
