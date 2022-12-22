package sampleprj;

class Account { // Thread가 아닌 공용 객체로 사용할 일반 객체
	// 생성자
	public Account() {
		
	}
	
	public Account(int balance) {
		super();
		this.balance = balance;
	}

	// 필드
	private int balance; // 잔액
	
	// 비즈니스 메서드
	
	// synchronized -> 동기화 메서드가 됨
	// 이 메서드를 먼저 실행한 Thread가 Lock(Monitor) 획득.
	// 이후 메서드가 리턴되지 않은 상태에서 다른 쓰레드가 메서드를 호출하면 블록됨.
	// 그럼 해당 쓰레드는 락을 다시 획득할 수 있을 때까지 홀드됨.
	// 앞선 쓰레드에 의해 실행된 메서드가 종료되면 다시 락을 돌려놓고, 뒤이은 쓰레드의 블록이 풀리게 됨.
	public synchronized void withdraw(int money) { 
		if(balance >= money) { // 출금 가능
			try {
				Thread.sleep(1000); // [디버그용으로 일단 현재 쓰레드 (이 메서드를 호출한 쓰레드)를 재움] 
			} catch (Exception e ) {
				
			}
			balance -= money;
		}
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}

class ThreadEx_12_1 implements Runnable { // Thread 만드는 방법 1. Thread 직접 상속 2. Runnable 인터페이스
	// 필드
	Account acc = new Account(1000);
	
	@Override
	public void run() {
		while(acc.getBalance() > 0) {
			int money = (int)(Math.random() * 3 + 1) * 100; //100, 200, 300 중 하나가 나오게 됨
			acc.withdraw(money);
			System.out.println("남은 금액: " + acc.getBalance());
		}
	}
}

public class ThreadEx_12 {
	public static void main(String[] args) {
		ThreadEx_12_1 r = new ThreadEx_12_1();
		
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		t1.run();
		t2.run();
	}
}
