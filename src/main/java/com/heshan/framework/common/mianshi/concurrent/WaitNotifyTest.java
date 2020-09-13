package com.heshan.framework.common.mianshi.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

public class WaitNotifyTest {

    static Thread t1=null;
    static Thread t2=null;
    static Object o1=new Object();
    static CountDownLatch downLatch=new CountDownLatch(1);
    public static void main(String[] args) {
        char a[]="abcdefg".toCharArray();
        char b[]="1234567".toCharArray();

        t1=new Thread(()->{
            try {
                downLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o1){
                for (int i = 0; i <a.length ; i++) {

                    System.out.println(a[i]);
                    try {
                        o1.notify();
                        o1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
                o1.notify();
            }

        }
        );

         t2=new Thread(()->{

             synchronized (o1) {
                 downLatch.countDown();

                 for (int i = 0; i < b.length; i++) {
                     System.out.println(b[i]);

                     o1.notify();
                     try {
                         o1.wait();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
                 o1.notify();

             }
        }

        );

         t1.start();
        t2.start();


    }


}
