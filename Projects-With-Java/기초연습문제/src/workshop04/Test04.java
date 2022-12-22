package workshop04;

public class Test04 {

	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("다시 입력해주세요");
			return;
		}
		
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int arr[][] = new int[a][b];
		
		for(int i=0; i<a; i++) {
			for(int j=0; j<b; j++) {
				arr[i][j] = (int)(Math.random() * 10 * 5 / 10) + 1;
			}
		}
		
		int sum = 0;
		for(int[] nar : arr) {
			for(int n : nar) {
				System.out.print(n + " ");
				sum += n;
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println("sum=" + sum);
		System.out.println("avg=" + (double)sum/a*b);
	}
}
