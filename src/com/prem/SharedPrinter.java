package com.prem;

class Printer {
	public synchronized void printDocument(String docName) {
		try {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() 
					+ " Printing Document " + docName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class SharedPrinter {

	public static void main(String[] args) throws InterruptedException{
		
		Printer p1 = new Printer();
		Thread t1 = new Thread(()->p1.printDocument("Doc1"));
		t1.start();
		while (t1.isAlive()) {
		    System.out.println("T1 state: " + t1.getState());
		    Thread.sleep(200);  
		}
		Thread t2 = new Thread(()->p1.printDocument("Doc1"));
		t2.start();
		while (t2.isAlive()) {
		    System.out.println("T2 state: " + t2.getState());
		    Thread.sleep(200);  
		}
		t2.sleep(1000);
		Thread t3 = new Thread(()->p1.printDocument("Doc2"));
		t3.start();
		while (t3.isAlive()) {
		    System.out.println("T3 state: " + t3.getState());
		    Thread.sleep(200);  
		}
		
		System.out.println("T1 final state: " + t1.getState());
		System.out.println("T2 final state: " + t2.getState());
		System.out.println("T3 final state: " + t3.getState());
		

	}

}
