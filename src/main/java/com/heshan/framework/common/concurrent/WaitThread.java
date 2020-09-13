package com.heshan.framework.common.concurrent;

public class WaitThread  extends Thread
{
    private static Object locker=new Object();
    
    public static void notifyTask() {
        synchronized (locker) {
            locker.notify();
        }
    }
    public void run() {
      //  while(true) {
           // synchronized (locker) {
                try {
                    System.out.println("thread="+Thread.currentThread().getId());
                    System.out.println("begin--"+System.currentTimeMillis()+"---");
                   // locker.wait(3000);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
          //  }
            try{
                Thread.sleep(10000);
                System.out.println("--"+System.currentTimeMillis()+"---");
            }catch(Throwable e){
            }
      //  }
    }
}
