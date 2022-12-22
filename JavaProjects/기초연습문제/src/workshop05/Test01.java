package workshop05;

public class Test01 {

	public static void main(String[] args) {
		String str = args[0];
		char[] chararr = str.toCharArray();
		
		for(int i=chararr.length-1;i>=0;i--) {
			System.out.print(chararr[i]);
		}
	}
}
