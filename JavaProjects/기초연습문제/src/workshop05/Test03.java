package workshop05;

public class Test03 {

	public static void main(String[] args) {
		int[][] arr2 = {
				{20, 30, 10},
				{50, 40, 60},
				{80, 80, 90}
		};
		
		for (int l=arr2.length-1; l>=0; l--) {
			for (int m=arr2[l].length-1; m>=0; m--) {
				System.out.print(arr2[l][m] + " ");
			}
		}
	}
}
