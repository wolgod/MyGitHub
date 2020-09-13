package com.heshan.framework.common.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 
 * @author Administrator
 * volatle并发情况下线程不安全 因为自增不是原子性操作
 */
public class VolatileTest1 {

	public static   AtomicInteger race=new AtomicInteger(0);
	//public static volatile int race=0;
	public static int THREAD_COUNT=20;
	public static void increase(){
		//race++;
		race.incrementAndGet();
	}
	
	static CountDownLatch count=new CountDownLatch(20);
	public static void main(String[] args) {
		
		Thread[] theads=new Thread[THREAD_COUNT];
		for (int i = 0; i < theads.length; i++) {
			theads[i]=new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j < 10000; j++) {
						increase();
					}
					count.countDown();
				}
			});
			
		theads[i].start();
		}
	    try {
			count.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(race);
		
	}
}
