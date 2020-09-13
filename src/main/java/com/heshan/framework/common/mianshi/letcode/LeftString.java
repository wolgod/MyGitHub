package com.heshan.framework.common.mianshi.letcode;

public class LeftString {


    public static  String reverseLeftWords(String s, int n) {
        String s1=s.substring(0,n);
        return  s.substring(n,s.length())+s1;
    }

    public static  int strToInt(String str) {
        String str1=str.trim();
        if(str1.length()<=0) return 0;
        int max=Integer.MAX_VALUE/10;
        char c[]=str1.toCharArray();
        int res=0;
        int index=0;
        Boolean isNegitive=false;
        if(str1.charAt(0)=='+'||str1.charAt(0)=='-'){
            if(str1.charAt(0)=='-'){
                isNegitive=true;
            }
            index=1;
        }
        for (int i = index; i <c.length ; i++) {

            if(Character.isDigit(c[i])) {
                if (isNegitive && res > max) return Integer.MIN_VALUE;
                if (isNegitive && res == max && c[i] > '8') return Integer.MIN_VALUE;

                if (!isNegitive && res > max) return Integer.MAX_VALUE;
                if (!isNegitive && res == max && c[i] > '7') return Integer.MAX_VALUE;

                res = res * 10 + c[i] - '0';
            }else{
                break;
            }

        }
        if(isNegitive){res=res*-1;}

        return res;

    }
    public static void main(String[] args) {

        String s = "lrloseumgh";
        int k = 6;
        System.out.println(strToInt("  0000000000012345678"));

        System.out.println(reverseLeftWords(s,k));
    }
}