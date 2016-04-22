package com.frank.utils.concurrent;

/**
 * @author <a href="mailto:heshanshao@ebnew.com">Administrator</a>
 * @version V1.0
 * @date 2016/4/6
 */
public class DeadLockDemo {
    private  static  String A="A";
    private  static  String B="B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }
    private  void deadLock(){

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    try {
                        Thread.currentThread().sleep(2000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("1");
                    }
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    synchronized (A){
                        System.out.println("2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
