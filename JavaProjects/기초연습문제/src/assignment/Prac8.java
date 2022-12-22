package assignment;

public class Prac8 {

	public static void main(String[] args) {
		char[] charArr = args[0].toLowerCase().toCharArray();
		
		for (int i=charArr.length-1; i>-1; --i) {
			System.out.print(charArr[i]);
		}
	}
}
