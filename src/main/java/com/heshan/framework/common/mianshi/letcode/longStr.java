package com.heshan.framework.common.mianshi.letcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长回文串
 * https://leetcode-cn.com/problems/longest-palindrome/
 */
public class longStr {


    public static int longestPalindrome(String s) {

        if(s.length()<=1) return s.length();
        char aa[]=s.toCharArray();
        int num=0;
        Map<Character,Integer> map=new HashMap<Character,Integer>();
        for (int i = 0; i <aa.length ; i++) {

            if(map.containsKey(aa[i])){
                map.put(aa[i],map.get(aa[i])+1);
            }else{
                map.put(aa[i],1);
            }
        }
        for (Integer value : map.values()) {
            num=num+value/2*2;
        }
        if (num==s.length()){
            return num;
        }
        if (num<s.length()){
            return num+1;
        }
        return num;

    }

    public static void main(String[] args) {
        String s="abbbaaaxcc";
        System.out.println(longestPalindrome(s));
    }
}
