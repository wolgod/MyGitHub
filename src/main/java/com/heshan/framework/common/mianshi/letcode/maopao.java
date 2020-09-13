package com.heshan.framework.common.mianshi.letcode;

import java.util.Arrays;

public class maopao {



    public static void maopao(int[] ss) {

       if(ss==null||ss.length<2) return;


        for (int i = 0; i <ss.length-1 ; i++) {
            boolean flag=true;
            for (int j = 0; j <ss.length-i-1 ; j++) {
                 if(ss[j]>ss[j+1]){
                     flag=false;
                     int temp=ss[j];
                     ss[j]=ss[j+1];
                     ss[j+1]=temp;
                 }
            }
            if (flag){
                return;
            }

        }
        System.out.println(Arrays.toString(ss));
    }
    public static void main(String[] args) {

        int aa[]=new int[]{3,1,3,2,7,9,5,8};

        maopao(aa);
    }
}
