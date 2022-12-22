package workshop03;

public class Test02 {

	public static void main(String[] args) {
		int sum = 0;
		int numOfitems = 0;
		int[][] arr2 = {
				{5, 5, 5, 5, 5},
				{10, 10, 10, 10, 10},
				{20, 20, 20, 20, 20},
				{30, 30, 30, 30, 30}
		};
		
		for (int i=0; i<arr2.length; i++) {
			for (int j=0; j<arr2[i].length; j++) {
				sum += arr2[i][j];
				numOfitems++;
			}
		}
		
		System.out.println("total=" + sum);
		System.out.println("avg=" + ((double)sum / numOfitems));
	}
}
