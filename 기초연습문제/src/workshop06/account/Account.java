package workshop06.account;

public class Account {
	private String account;
	private double balance;
	private double interestRate;
	
	Account() {
		
	}

	public Account(String account, double balance, double interestRate) {
		super();
		this.account = account;
		this.balance = balance;
		this.interestRate = interestRate;
	}
	
	public double calculaterInterest( ) {
		return balance * interestRate;
	}
	
	public void deposit(double money) throws Exception {
		if (money < 0)
			throw new Exception("입금 금액이 0보다 작습니다.");
		balance += money;
	}
	
	public String getAccount() {
		return account;
	}

	public double getBalance() {
		return balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void withdraw(double money) throws Exception{
		StringBuffer errMsg = new StringBuffer();
		if (money < 0)
			errMsg.append("출금 금액이 0보다 작습니다.");
		if (money > balance)
			errMsg.append("출금 금액이 잔액보다 많습니다.");
		
		if (errMsg.length() > 0)
			throw new Exception(errMsg.toString());
		else
			balance -= money;
	}
	
	@Override
	public String toString() {
		return String.format("%s %.1f %.1f", account, balance, interestRate);
	}
}
