package com.frank.utils.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:heshanshao@ebnew.com">Administrator</a>
 * @version V1.0
 * @date 2016/4/6
 */
public class stopThread {

    private  static  volatile  boolean stopRequest;

    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {

                int i=0;
                while (!stopRequest){
                    i++;
                }
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequest=true;
    }

}
