package sampleprj;

class TestThread extends Thread {
	@Override
	public void run() {
		int count = 10;
		while(count-- > 0) {
			System.out.println(toString() + ": " + count);
		}
		System.out.println(toString() + ": 카운트다운 끝");
	}
}

class TestRunnable implements Runnable {
	@Override
	public void run() {
		int count = 10;
		while(count-- > 0) {
			System.out.println(toString() + ": " + count);
		}
		System.out.println(toString() + ": 카운트다운 끝");
	}
}

public class ThreadPrac01 {
	public static void main(String[] args) {
		TestThread t1 = new TestThread();
		
		TestRunnable r = new TestRunnable();
		Thread t2 = new Thread(r);
		
		t1.start();
		t2.start();
	}
}
