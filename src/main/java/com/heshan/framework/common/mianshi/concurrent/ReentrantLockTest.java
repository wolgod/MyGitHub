package com.heshan.framework.common.mianshi.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 当当前线程调用condition.await()方法后，会使得当前线程释放lock然后加入到等待队列中，
 * 直至被signal/signalAll后会使得当前线程从等待队列中移至到同步队列中去，
 * 直到获得了lock后才会从await方法返回，或者在等待时被中断会做中断处理
 */
public class ReentrantLockTest {

    static Lock reentrantLock=new ReentrantLock();
    static Condition conditionA=reentrantLock.newCondition();
    static Condition conditionB=reentrantLock.newCondition();
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
                    conditionB.await();
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
                    conditionB.signal();
                    conditionA.await();
                }
                conditionB.signal();

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
