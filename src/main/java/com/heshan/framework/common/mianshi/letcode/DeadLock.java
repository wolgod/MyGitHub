package com.heshan.framework.common.mianshi.letcode;

public class DeadLock {

    public  static  Object  o1=new Object();
    public  static  Object  o2=new Object();




    public static void main(String[] args) {

        Thread a=new Thread(){

            @Override
            public void run() {
                System.out.println("A running");
                synchronized (o1){
                    System.out.println("Lock O1");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized(o2){
                        System.out.println("Thread A");
                    }
                }
            }
        };

        Thread B=new Thread(){

            @Override
            public void run() {
                System.out.println("B running");

                synchronized (o2){
                    System.out.println("Lock o2");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized(o1){
                        System.out.println("Thread B");
                    }
                }
            }
        };
        a.start();
        B.start();
    }

}
