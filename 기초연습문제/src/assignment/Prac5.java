package assignment;

public class Prac5 {
	public static void main(String[] args) {
		String a = args[0];
		String b = args[1];
		String c = args[2];
		int nums[] = {Integer.parseInt(a), Integer.parseInt(b), Integer.parseInt(c)};
		
		int max = 1;
		int min = 1;
		for (int i=0; i<3; i++) {
			if (nums[i] > max) max = nums[i];
			if (nums[i] < min) min = nums[i];
		}
		
		System.out.printf("입력값: %s, %s, %s\n", a, b, c);
		System.out.println("최대값: " + max);
		System.out.println("최소값: " + min);
	}
}
