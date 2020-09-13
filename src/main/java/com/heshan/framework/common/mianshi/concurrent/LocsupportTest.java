package com.heshan.framework.common.mianshi.concurrent;

import java.util.concurrent.locks.LockSupport;

public class LocsupportTest {

    static Thread t1=null;
    static Thread t2=null;

    public static void main(String[] args) {
        char a[]="abcdefg".toCharArray();
        char b[]="1234567".toCharArray();

        t1=new Thread(()->{
            for (int i = 0; i <a.length ; i++) {
                System.out.println(a[i]);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }
        );

         t2=new Thread(()->{
            for (int i = 0; i <b.length ; i++) {
                LockSupport.park();
                System.out.println(b[i]);
                LockSupport.unpark(t1);
            }
        }

        );
         t1.start();
         t2.start();

    }


}
