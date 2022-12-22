package workshop04;

public class Test06 {

	public static void main(String[] args) {
		Account[] accounts = new Account[5];
		
		for (int i=0; i<accounts.length; i++) {
			accounts[i] = new Account("221-0101-211" + i, 100000, 4.5);
		}
		
		for (Account ac : accounts) {
			ac.accountInfoLn();
		}
		
		for (Account ac : accounts) {
			ac.setInterestRate(3.7);
			ac.accountInfo();
			System.out.printf(" 이자: %.1f원%n", ac.calculateInterest());
		}
	}
}
