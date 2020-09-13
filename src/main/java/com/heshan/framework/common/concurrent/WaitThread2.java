package com.heshan.framework.common.concurrent;

import java.util.Random;

public class WaitThread2  extends Thread
{
    private static Object locker=new Object();
    
    WaitThread w;
    
    public WaitThread2(WaitThread w){
        this.w=w;
    }
    
    public void run() {
        while(true) {
            synchronized (locker) {
                try {
                    int a=(new Random().nextInt(10)+1)*100;
                    System.out.println(a);
                    locker.wait(a);// 
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            try{
                w.notifyTask();
            }catch(Throwable e){
            }
        }
    }
}
