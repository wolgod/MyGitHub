package com.heshan.framework.common.mianshi.letcode;

import java.util.Arrays;

public class quikSort {

    public static void quickSort(int[] array) {

        if (array.length==0||array.length==1){
            return;
        }
        quick(array,0,array.length-1);

    }
    public static void quick(int[] arr,int start,int end){
        if(start >=end) {
            return;
        }
        int provit=arr[start];
        int low=start;
        int high=end;
        while (low<high){
            while (low<high&&arr[high]>=provit){
                high--;
            }
            if(low<high)
            arr[low++]=arr[high];

            while (low<high&&arr[low]<=provit){
                low++;
            }
            if (low<high)
            arr[high--]=arr[low];
        }
        arr[low]=provit;

        quick(arr,start,low);
        quick(arr,low+1,end);


    }

    public static void main(String[] args) {

          int aa[]=new int[]{3,1,3,2};

          quickSort(aa);

        System.out.println(Arrays.toString(aa));

      }
    }
