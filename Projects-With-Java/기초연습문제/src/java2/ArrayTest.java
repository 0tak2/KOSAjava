package java2;

public class ArrayTest {

	public static void main(String[] args) {
		int[][] array = {
				{12 , 41 , 36 , 56},
				{82 , 10, 12 , 61},
				{14, 16, 18, 78},
				{45, 26, 72, 23}
		};
		int sum = 0;
		double avg;
		
		for(int i=0; i<4; ++i) {
			for(int j=0; j<4; ++j) {
				sum += array[i][j];
			}
		}
		avg = sum / 16.0;
		
		System.out.println("합계: " + sum);
		System.out.println("합계: " + avg);
	}
}
