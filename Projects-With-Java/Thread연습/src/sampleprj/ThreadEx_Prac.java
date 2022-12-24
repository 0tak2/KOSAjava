package sampleprj;

class sharedObj {
	public synchronized void sayHello(SayHelloThread t) {
		System.out.println(t.getName() + ": Hello! My name is " + t.getName());
		notify(); // wait된 쓰레드를 깨움
        try {
            wait(); // 한 번 말하고 일단 멈춤
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}

class SayHelloThread extends Thread {
	sharedObj shared;
	
	SayHelloThread(sharedObj shared) {
		this.shared = shared;
	}

	@Override
	public void run() {
		for(int i=0; i<200; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			shared.sayHello(this);
		}
	}
}

public class ThreadEx_Prac {
	public static void main(String[] args) {
		sharedObj shared = new sharedObj();
		SayHelloThread t0 = new SayHelloThread(shared);
		SayHelloThread t1 = new SayHelloThread(shared);
		
		t0.start();
		t1.start();
	}
}
