package com.heshan.framework.common.mianshi.letcode;

import java.util.Arrays;

public class insertSort {

    public static void insertSort(int[] array) {

        if (array.length==0||array.length==1){
            return;
        }
        for(int i=1;i<array.length;i++){
              int j=i-1;
              int tmp=array[i];
              while (j>=0&&array[j]>tmp){
                  array[j+1]=array[j];
                  j--;
              }
              array[j+1]=tmp;

        }

    }

    public static void main(String[] args) {

          int aa[]=new int[]{3,1,3,2};

        insertSort(aa);

        System.out.println(Arrays.toString(aa));

      }
    }
