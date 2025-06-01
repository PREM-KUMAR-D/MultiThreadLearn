package com.prem;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

class MyRunnable implements Runnable {

	Counter c;
	ReentrantLock lock;

	public MyRunnable(Counter c, ReentrantLock lock) {
		// TODO Auto-generated constructor stub
		this.c = c;
		this.lock = lock;
	}

	@Override
	public void run() {

		System.out.println(Thread.currentThread().getName() + " count is : " + c.count);
		int attempts = 0;
		while (attempts < 5) {
			try {
				if (lock.tryLock()) {
					try {

						System.out.println(Thread.currentThread().getName() + " Acquired the lock");
						for (int i = 0; i < 1000; i++) {
							c.count = c.count + 1;
						}
						System.out.println("Acquired lock count is " + c.count);

					} finally {
						System.out.println(Thread.currentThread().getName() + " Releasing the acquired lock");
						lock.unlock();
					}
					break;
				} else {
					attempts++;
					System.out.println(Thread.currentThread().getName() + " failed to acquire lock");
					Thread.sleep(500);

				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
		
		if (attempts == 5) {
			System.out.println("No of attempts crossed so " + Thread.currentThread().getName());
		}
		

	}
}

class Counter {
	int count = 1;

}

class test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int limit = 5;

		Thread[] t = new Thread[5];
		Counter c = new Counter();
		ReentrantLock lock = new ReentrantLock();

		for (int i = 0; i < limit; i++) {
			t[i] = new Thread(new MyRunnable(c, lock));
			t[i].start();
		}

		for (Thread th : t) {
			th.join();
		}

	}

}
