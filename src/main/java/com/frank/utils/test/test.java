package com.frank.utils.test;

/**
 * @author <a href="mailto:heshanshao@ebnew.com">Administrator</a>
 * @version V1.0
 * @date 2016/3/25
 */
public class test {
    public static void main(String[] args) {
        String s1 = "ab123" ;
        String s2 = new String( "ab123" ).intern() ;
        System.out.println( s1 == s2 );
        String s3 = s2.intern() ;
        System.out.println( s1 == s3 ) ;
    }
}
