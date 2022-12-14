package assignment;

class Months {
	private int month;
	
	Months() {
	}
	
	
	public Months(int month) {
		super();
		this.month = month;
	}


	public int getDays() {
		switch(month) {
			case 9:
			case 4:
			case 6:
			case 11:
				return 30;
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 2:
				return 28;
			default:
				return -1;
		}
	}
}

public class Prac6 {
	public static void main(String[] args) {
		int month = Integer.parseInt(args[0]);
		
		if (args.length > 1) {
			System.out.println("다시 입력해 주세요");
			return;
		}
		
		if (month < 1 && month > 12) {
			System.out.println("입력된 값이 잘못되었습니다");
			return;
		}
		
		Months m = new Months(month);
		
		System.out.printf("입력 받은 월: %d월\n", month);
		System.out.printf("마지막 일자: %d일\n", m.getDays());
	}
}
