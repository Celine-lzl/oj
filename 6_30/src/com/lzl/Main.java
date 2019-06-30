package com.lzl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main2(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            ArrayList<Integer> list = new ArrayList<>();
            int[] data = new int[10];
            // 将0~9每个数字各有多少个先保存起来
            for(int i = 0; i < 10; i++){
                data[i] = scanner.nextInt();
            }
            // 把除了0之外的数都存入list
            for(int i = 1; i < 10; i++){
                for(int j = 0; j < data[i] ; j++){
                    list.add(i);
                }
            }
            // 输出第一个非0数
            System.out.print(list.get(0));
            // 输出0
            for(int i = 0; i < data[0]; i++){
                System.out.print(0);
            }
            // 从list中输出剩余的数
            for(int i = 1; i < list.size(); i++){
                System.out.print(list.get(i));
            }
            System.out.println();
        }
    }

    /**
     * d13_2 超长正整数相加
     *  直接采用java BigInteger类
     *  相加是add
     */
    public static void main1(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String n = scanner.next();
            String m = scanner.next();
            BigInteger  n1 = new BigInteger(n);
            BigInteger  m1 = new BigInteger(m);
            System.out.println(m1.add(n1));
        }
    }

    /**
     * d13_1 跟奥巴马一起编程
     * 将所要打印的图形拆成三部分
     * 第一行
     * 中间行：第一列和最后一列，以及中间列的空格
     * 最后一行
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            String s = sc.next();
            // 先输出第一行
            for(int i = 0; i < n; i++){
                System.out.print(s);
            }
            System.out.println();
            // 输出中间行，共n/2-2行
            for(int i = 0; i < ((n/2)+(n%2))-2; i++){
                // 先输出最左左右两列的字符
                System.out.print(s);
                // 在输出中间行中间列的空格
                for(int j = 0; j < n-2; j++){
                    System.out.print(" ");
                }
                System.out.print(s);
                System.out.println();
            }
            // 输出最后一行
            for(int i = 0; i < n; i++){
                System.out.print(s);
            }
        }
    }
}
