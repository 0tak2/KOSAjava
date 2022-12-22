package sampleprj;

class SimpleRunnable implements Runnable {
	@Override
	public void run() {
		try {
			throw new Exception("고의로 예외를 발생시켰습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class ThreadPrac02 {
	public static void main(String[] args) {
		Thread t1 = new Thread(new SimpleRunnable());
		t1.start();
		//t1.run();
	}
}
