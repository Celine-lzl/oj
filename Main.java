package com.lzl;

import java.math.BigInteger;
import java.util.*;
public class Main{
    /**
     * d33_1 锤子剪刀布
     */
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                int n = sc.nextInt();
                int v = 0;
                int f = 0;
                Map<Character, Integer> mapA = new TreeMap<>();
                Map<Character, Integer> mapB = new TreeMap<>();
                for (int i = 0; i < n; i++) {
                    char a = sc.next().charAt(0);
                    char b = sc.next().charAt(0);
                    int re = match(a, b);
                    if (re == 1) {
                        v++;
                        if (mapA.containsKey(a)) {
                            mapA.put(a, mapA.get(a) + 1);
                        } else {
                            mapA.put(a, 1);
                        }

                    } else if (re == -1) {
                        f++;
                        if (mapB.containsKey(b)) {
                            mapB.put(b, mapB.get(b) + 1);
                        } else {
                            mapB.put(b, 1);
                        }
                    }
                }
                System.out.println(v + " " + (n - v - f) + " " + f);
                System.out.println(f + " " + (n - v - f) + " " + v);
                int max = 0;
                for (Map.Entry<Character, Integer> m : mapA.entrySet()) {
                    if (m.getValue() > max) {
                        max = m.getValue();
                    }
                }
                if (max == 0) {
                    System.out.print("B");
                } else {
                    for (Map.Entry<Character, Integer> m : mapA.entrySet()) {
                        if (m.getValue() == max) {
                            System.out.print(m.getKey());
                            break;
                        }
                    }
                }
                max = 0;
                System.out.print(" ");
                for (Map.Entry<Character, Integer> m : mapB.entrySet()) {
                    if (m.getValue() > max) {
                        max = m.getValue();
                    }
                }
                if (max == 0) {
                    System.out.print("B");
                } else {
                    for (Map.Entry<Character, Integer> m : mapB.entrySet()) {
                        if (m.getValue() == max) {
                            System.out.print(m.getKey());
                            break;
                        }
                    }
                }
            }
        }
        public static int match(char a, char b) {
            if (a == b) {
                return 0;
            } else if ((a == 'C' && b == 'J') || (a == 'J' && b == 'B') || (a == 'B' && b == 'C')) {
                return 1;
            } else {
                return -1;
            }
        }

    /**
     * d31_1 说反话
     * 对输入的字符串按空格分割后存入数组
     * 将数组倒序输出
     */
    public static void main3(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            String[] data = s.split(" ");
            for(int i = data.length-1; i >= 0; i--){
                System.out.print(data[i]+" ");
            }
        }
    }


    /**
     * d30_2 大整数排序
     */
    public static void main2(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextBigInteger()){
            int n = sc.nextInt();
            BigInteger[] data = new BigInteger[n];
            for(int i = 0; i < n; i++){
                data[i] = sc.nextBigInteger();
            }
            selectSort(data);
            // 排序后遍历输出
            for(int i = 0; i < n; i++){
                System.out.println(data[i]);
            }
        }
    }
    // 进行选择排序
    public static void selectSort(BigInteger[] data){
        for(int i = 0; i < data.length; i++){
            int minindex = i; // 最小数下标
            BigInteger min = data[minindex]; // 最小数的值
            for(int j = i; j < data.length; j++){
                // 如果data[minindex] > data[j]
                if(data[minindex].compareTo(data[j]) == 1){
                    minindex = j;
                    min = data[j];
                }
            }
            // 交换
            data[minindex] = data[i];
            data[i] = min;
        }
    }
}