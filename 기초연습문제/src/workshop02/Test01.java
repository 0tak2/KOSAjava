package workshop02;

public class Test01 {

	public static void main(String[] args) {
		int x = 15;
		
		if(x > 10 && x <20) {
			System.out.println("1: ture");
		}
		
		char ch1 = ' ';
		if(ch1 == ' ' || ch1 != '\t') {
			System.out.println("2: true");
		}
		
		char ch2 = 'x';
		if(ch2 == 'X' || ch2 == 'x') {
			System.out.println("3: true");
		}
		
		char ch3 = '0';
		if(ch3 >= 48 && ch3 <= 57) {
			System.out.println("4: true");
		}
		
		char ch4 = 'a';
		if(ch4 >= 65 && ch3 <= 90) {
			System.out.println("5: true");
		}
		
		int year = 400;
		if(year % 400 == 0 ||
				(year % 4 == 0 && year % 100 != 0)) {
			System.out.println("6: true");
		}
		
		boolean powerOn = false;
		if(!powerOn) {
			System.out.println("7: true");
		}
		
		String str = "yes";
		if(str.equals("yes")) {
			System.out.println("8: true");
		}
		
	}
}
