package workshop03;

public class Test05 {

	public static void main(String[] args) throws Exception {
		for(int i=0; i<4; i++) {
			String temp;
			try {
				temp = args[i];
			} catch (Exception e) {
				throw new Exception("인자를 잘못 입력했습니다");
			}
			if (temp == null)
				throw new Exception("인자를 잘못 입력했습니다");
		}
		
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int c = Integer.parseInt(args[2]);
		int d = Integer.parseInt(args[3]);
		
		int sum = Calc.calcSum(a, b, c, d);
		double avg = (double)sum / 4;
		char grade;
		if (avg >= 90 && avg <= 100) {
			grade = 'A';
		} else if (avg >= 70 && avg < 90) {
			grade = 'B';
		} else if (avg >= 50 && avg < 70) {
			grade = 'C';
		} else if (avg >= 30 && avg < 50) {
			grade = 'D';
		} else {
			grade = 'F';
		}
			
		System.out.println("Sum: " + sum);
		System.out.println("Avg: " + avg);
		System.out.println(grade +"학점");
	}
}

class Calc {
	public static int calcSum(int a, int b, int c, int d) {
		return a + b + c + d;
	}
}
