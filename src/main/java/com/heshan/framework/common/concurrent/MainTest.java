package com.heshan.framework.common.concurrent;

public class MainTest
{
    public static void main(String[] args)
    {
        WaitThread t=new WaitThread();
        t.start();
        System.out.println(1111111);
        System.out.println("main thread="+Thread.currentThread().getId());

/*        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        WaitThread2 t2=new WaitThread2(t);
        t2.start();*/

    }
}
