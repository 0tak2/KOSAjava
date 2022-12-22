package sampleprj;

class TestThread2 extends Thread {
	@Override
	public void run() {
		int count = 10;
		while(count-- > 0) {
			System.out.println(getName() + ": " + count);
		}
		System.out.println(getName() + ": 카운트다운 끝");
	}
}

class TestRunnable2 implements Runnable {
	@Override
	public void run() {
		int count = 10;
		while(count-- > 0) {
			System.out.println(Thread.currentThread().getName() + ": " + count);
		}
		System.out.println(Thread.currentThread().getName() + ": 카운트다운 끝");
	}
}

public class ThreadPrac03 {
	public static void main(String[] args) {
		TestThread2 t1 = new TestThread2();
		
		TestRunnable2 r = new TestRunnable2();
		Thread t2 = new Thread(r);
		
		t1.start();
		t2.start();
	}
}
