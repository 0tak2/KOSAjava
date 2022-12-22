package workshop03;

import java.util.Arrays;

public class Test04 {

	public static void main(String[] args) {
		int[] arr3 = new int[5];
		int lastIndex = -1;
		int sum = 0;
		
		while(lastIndex < arr3.length - 1) {
			while(true) {
				int random = (int) (Math.random() * 10);
				boolean dataCheck = true;
				for (int i : arr3) {
					if (i == random) {
						dataCheck = false;
					}
				}
				
				if (dataCheck) {
					arr3[lastIndex + 1] = random;
					lastIndex++;
					break;
				}
			}	
		}
		
		for (int i : arr3) {
			sum += i;
		}
		
		System.out.println("Generated Arr: " + Arrays.toString(arr3));
		System.out.println("sum=" + sum);
		System.out.println("avg=" + (double)sum/arr3.length);
	}
}
