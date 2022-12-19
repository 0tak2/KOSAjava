package workshop06.account;

public class AccountTest {
	public static void main(String[] args) {
		Account acc = new Account("441-0290-1203", 500000, 7.3);
		System.out.println("계좌정보: " + acc.toString());
		
		// -10원 입금
		try {
			acc.deposit(-10);
		} catch (Exception e) {
			System.err.println("예외가 발생했습니다. " + e);
		}
		
		// 600000원 출금
		try {
			acc.withdraw(600000);
		} catch (Exception e) {
			System.err.println("예외가 발생했습니다. " + e);
		}
		
		System.out.println("이자: " + acc.getInterestRate());
		
	}
}
