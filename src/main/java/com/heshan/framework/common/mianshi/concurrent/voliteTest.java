package com.heshan.framework.common.mianshi.concurrent;

public class voliteTest {

    static Thread t1=null;
    static Thread t2=null;
    static Object o1=new Object();
    static  volatile  boolean flag=true;
    public static void main(String[] args) {
        char a[]="abcdefg".toCharArray();
        char b[]="1234567".toCharArray();

        t1=new Thread(()->{


                for (int i = 0; i <a.length ; i++) {
                    while (!flag){}
                    System.out.println(a[i]);
                    flag=false;

                }


        }
        );

         t2=new Thread(()->{


                 for (int i = 0; i < b.length; i++) {
                     while(flag){}
                     System.out.println(b[i]);
                     flag=true;

                 }

        }

        );

         t1.start();
        t2.start();


    }


}
