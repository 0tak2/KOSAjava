package workshop02;

public class Test06 {

	public static void main(String[] args) {
		int i = 2;
		
		while (i <= 8) {
			for (int j=1; j<=3; j++) {
				System.out.printf("%d*%d=%d\t", i, j, i*j);
				System.out.printf("%d*%d=%d\t", i+1, j, (i+1)*j);
				if (i+2 < 10)
					System.out.printf("%d*%d=%d\t", i+2, j, (i+2)*j);
				System.out.println();
			}
			System.out.println();
			i += 3;
		}
	}
}
