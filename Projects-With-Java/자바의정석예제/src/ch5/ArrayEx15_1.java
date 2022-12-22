package ch5;

import java.util.Scanner;

public class ArrayEx15_1 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			String str = sc.next();
			
			char[] 암호표 = "가나다라마바사아자차카타파하거너더러머버서어저처커터퍼".toCharArray();
			
			for (int i=0; i<str.length(); i++) {
				System.out.print(암호표[str.charAt(i) - 'A']);
			}

		}
	}
}
