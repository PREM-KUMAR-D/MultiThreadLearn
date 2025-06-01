//package com.prem;
//
//class OddThread extends Thread {	
//	
//	public Counter c ;
//	 
//	public OddThread(Counter c) {
//		// TODO Auto-generated constructor stub
//		this.c  = c;
//	}
//	
//	
//	@Override
//	public void run() {
//		while(c.count < 20) {
//			synchronized(c) {
//				while(!c.isOdd) {
//					try {
//						c.wait();
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				System.out.println("Printing odd value : "+ c.count);
//				c.isOdd = false;
//				c.count = c.count +1;
//				c.notify();
//			}
//		}
//		
//	}
//}
//class EvenThread extends Thread {	
//	
//	public Counter c;
//	
//	public EvenThread(Counter c) {
//		this.c  =  c;
//	}
//	
//	@Override
//	public void run() {
//		while(c.count  < 20){
//			synchronized(c) {
//				while(c.isOdd) {
//					try {
//						c.wait();
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				System.out.println("Printing even value : "+ c.count);
//				c.isOdd = true;
//				c.count = c.count +1;
//				c.notify();
//			}
//			
//		}
//	}
//}
//
//class Counter {
//	public volatile int count =1;
//	public volatile boolean isOdd = true;
//}
//
//
//
//class Main {
//
//	public static void main(String[] args)  throws InterruptedException{
//		// TODO Auto-generated method stub
//		
//		Counter c = new Counter();
//		
//		
//		EvenThread e = new EvenThread(c);
//		OddThread o = new OddThread(c);
//		
//		e.start();
//		o.start();
//		
//		
//		
//
//	}
//
//}
