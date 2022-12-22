package sampleprj;

//공유 객체를 생성하기 위한 Class
class Shared {
	
	// 공유 객체의 공유 메소드
	public synchronized void printName() {
		try {
			for(int i=0; i<10; i++) {
				// 나: 교수님 구현 내용처럼 for문이 공유 객체 안에 있는게 맞다.
				// wait()과 notify()는 임계영역 안에서 시간이 오래 걸리는 코드에 대해
				// 다른 쓰레드가 실행될 수 있도록 핸들링 하는 것이기 때문.
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName());
				notify();
				wait();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

// 앞으로는 쓰레드 구현 시 Runnable을 구현하여 만드는 것이 좋음
class ThreadEx_13_1 implements Runnable {
	public ThreadEx_13_1() {
	}
	
	public ThreadEx_13_1(Shared shared) {
		super();
		this.shared = shared;
	}

	private Shared shared;
	
	@Override
	public void run() {
		shared.printName();
	}
}

public class ThreadEx_13 {
	public static void main(String[] args) {
		// 공유 객체
		Shared shared = new Shared();
		
		ThreadEx_13_1 runnable1 = new ThreadEx_13_1(shared);
		ThreadEx_13_1 runnable2 = new ThreadEx_13_1(shared);
		
		Thread t1 = new Thread(runnable1, "첫째");
		Thread t2 = new Thread(runnable2, "둘째");
		
		t1.start();
		t2.start();
	}
}
