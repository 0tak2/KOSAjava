package workshop04;

class Account {
	private String account;
	private int balance;
	private double interestRate;
	
	Account() {
		
	}

	public Account(String account, int balance, double interestRate) {
		super();
		this.account = account;
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getBalance() {
		return balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public double calculateInterest() {
		return balance * interestRate * 0.01;
	}
	
	public void deposit(int money) {
		balance += money;
	}
	
	public void withdraw(int money) {
		balance -= money;
	}
	
	public String toString() {
		return String.format("계좌정보: %s 현재잔액: %d 이자율: %.1f%%", account, balance, interestRate);
	}
	
	public void accountInfoLn() {
		System.out.println(toString());
	}
	
	public void accountInfo() {
		System.out.print(toString());
	}
}

public class Test05 {

	public static void main(String[] args) {
		Account account = new Account("441-0290-1203", 500000, 7.3);
		System.out.println(account.toString());
		
		account.deposit(20000);
		System.out.println(account.toString());
		
		System.out.println("이자: " + account.calculateInterest());
	}
}
