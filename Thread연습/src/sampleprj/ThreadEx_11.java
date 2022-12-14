package sampleprj;

// 이 쓰레드의 인스턴스를 생성하여 실행하면
// 10초마다 변수의 값(메모리 사용량 비유)을 감소시킴

class ThreadEx_11_1 extends Thread {
	final static int MAX_MEMORY = 1000; // 상수값은 스네이크 케이스
	int usedMemory = 0; // 전체 중 사용 값

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(10000); //10000 = 10초
			} catch (InterruptedException e) { // sleep 중 인터럽트 익셉션을 발생시키면,

			}									// 즉시 빠져나와서,
			gc();								// gc 발동
			System.out.println("남은 메모리량: " + freeMemory());
		}
	}

	public void gc() {
		usedMemory -= 300;
		if(usedMemory < 0) {
			usedMemory = 0;
		}
	}
	
	public int totalMeomory() {
		return MAX_MEMORY;
	}
	
	public int freeMemory() {
		return MAX_MEMORY - usedMemory;
	}
}

public class ThreadEx_11 {
	public static void main(String[] args) {
		ThreadEx_11_1 t = new ThreadEx_11_1();
		t.setDaemon(true); // 쓰레드의 루프가 무한 루프이기 때문에 데몬 쓰레드로 만들지 않으면 프로그램이 안죽음. 데몬쓰레드로 만들면 부모 쓰레드가 죽으면 같이 죽음.
		t.start();
		
		int requiredMemory = 0;
		
		for (int i = 0; i < 20; i++) {
			requiredMemory = (int)(Math.random() * 10) * 20; //Math.random()은 0 ~ 1을 반환하므로 0 ~ 180
			// 0, 20, 40, ..., 180
			
			// 위에서 정해진 필요 메모리 량이 사용할 수 있는 메모리 양보다 크거나
			// 혹은 전체 메모리의 60% 이상을 사용했을 때 GC를 호출
			if((t.freeMemory() < requiredMemory) ||
					t.freeMemory() < t.totalMeomory() * 0.4) {
				t.interrupt(); // InterruptedException 발생 -> gc() => while문 루프 다시 돔
				
				try {
					t.join(100); // t가 끝날 때까지 N초 기다려라; N초 후 다시 메인 쓰레드 동작.
				} catch (InterruptedException e) {
				} // 
			}
			
			t.usedMemory += requiredMemory; // 메모리량 누적
			System.out.println("남은 메모리량: " + t.freeMemory());
		}
	}
}