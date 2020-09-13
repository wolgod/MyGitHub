package com.heshan.framework.common.mianshi.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock1Test {

    static Lock reentrantLock=new ReentrantLock();
    static Condition conditionA=reentrantLock.newCondition();
    static  Thread t1=null;
    static Thread t2=null;

    public static void main(String[] args) {
        char a[]="abcdefg".toCharArray();
        char b[]="1234567".toCharArray();

        t1=new Thread(()->{
               try {
                   reentrantLock.lock();

                for (int i = 0; i <a.length ; i++) {
                    System.out.println(a[i]);
                    conditionA.signal();
                    conditionA.await();
                }
                  conditionA.signal();
               }
               catch (InterruptedException e) {
                   e.printStackTrace();
               }finally {
                   reentrantLock.unlock();
               }
        }
        );

        t2=new Thread(()->{
            try {
                reentrantLock.lock();
                for (int i = 0; i <b.length ; i++) {
                    System.out.println(b[i]);
                    conditionA.signal();
                    conditionA.await();
                }
                conditionA.signal();

            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        }
        );

         t1.start();
        t2.start();


    }


}
