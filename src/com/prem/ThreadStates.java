package com.prem;

class BlockedExample {
    public synchronized void doSomething() {
        try {
            Thread.sleep(1000); // Hold the lock
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class ThreadStates {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		BlockedExample obj = new BlockedExample();

		Thread t1 = new Thread(() -> obj.doSomething());
		Thread t2 = new Thread(() -> obj.doSomething());

		t1.start();
		Thread.sleep(100); 
		t2.start();

		Thread.sleep(200); 
		System.out.println("t2 state (should be BLOCKED): " + t2.getState());
		
		
		Thread t3 = new Thread(() -> {
		    try {
		        Thread.sleep(1000);
		    } catch (InterruptedException e) {
		        Thread.currentThread().interrupt();
		    }
		});

		Thread t4 = new Thread(() -> {
		    try {
		        t3.join(); // Will go into WAITING state
		    } catch (InterruptedException e) {
		        Thread.currentThread().interrupt();
		    }
		});

		t3.start();
		t4.start();
		Thread.sleep(200); // give time for t4 to call join

		System.out.println("t4 state (should be WAITING): " + t4.getState());
		
		
			
	}

}
