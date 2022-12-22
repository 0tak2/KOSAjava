package workshop02;

import java.util.Objects;

public class Test07 {

	public static void main(String[] args) {
		int input;
		int sum = 0;
		try {
			Objects.requireNonNull(args[0]);
			input = Integer.parseInt(args[0]);
		} catch (Exception e) {
			System.out.println("인자가 입력되지 않았습니다.");
			return;
		}
		
		for(int i=1; i<=100; i++) {
			if (i % input != 0) {
				continue;
			}
			sum += i;
		}
		
		System.out.println(sum);
		
	}
}
