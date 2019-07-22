package com.lzl;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int[] a = new int[]{3,5,1,99,24,0,523,21,1};
        Arrays.sort(a);
        System.out.println(a[a.length-1]);
        System.out.println("----------------");
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
    }
}
