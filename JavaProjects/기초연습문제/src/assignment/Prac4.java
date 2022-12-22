package assignment;

public class Prac4 {
	public static void main(String[] args) {
		for(int l=0; l<6; l++) {
			for(int m=0; m<6; m++) {
				for(int n=0; n<6; n++) {
					int result = l * m * n;
					
					if (result != 0 && result % 3 == 0) {
						System.out.println(l + "*" + m + "*" + n + " = " + result);	
					}
				}
			}
		}
	}
}
