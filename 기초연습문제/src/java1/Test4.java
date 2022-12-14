package java1;

public class Test4 {

	public static void main(String[] args) {
		for (int i=2; i<=5; i++) {
			for (int j=1; j<=9; j++) {
				int times = i * j;
				if (times % 2 != 0) {
					System.out.printf("%d * %d = %d\n", i, j, times);
				}
			}
		}
	}
}
