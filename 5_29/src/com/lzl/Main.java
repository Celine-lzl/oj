package com.lzl;

import java.util.Scanner;

//public class Main{
// day9  回文解码
//    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        while(scanner.hasNext()){
//            StringBuffer org = new StringBuffer(scanner.nextLine());
//            int n = scanner.nextInt();
//            for(int i = 0; i < n; i++){
//                int begin = scanner.nextInt();
//                int end = scanner.nextInt(); // 最后一个位置坐标
//                StringBuffer sb = new StringBuffer(org.substring(begin,begin+end));
//                org.insert(begin+end,sb);
//            }
//            System.out.println();
//        }
//    }

// day7 求最小公倍数
//public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        int m;
//        int n;
//        int a;
//        int b;
//        while(scanner.hasNext()){
//            m = scanner.nextInt();
//            n = scanner.nextInt();
//            a = m; // a代表大的数
//            b = n; // b代表小的数
//            if(m < n){
//                a = n;
//                b = m;
//            }
//            System.out.println(a * b / gcd(a,b));
//        }
//    }
//    // 辗转相除法求两个数的最小公约数
//    // 两个数的最小公倍数等于两数积除以最小公约数
//    public static int gcd(int a, int b){
//        while(a % b != 0){
//            int n = a % b;
//            a = b;
//            b = n;
//        }
//        return b;
//    }

// day6 n个数里大于等于n/2个数
//        public static void main(String[] args){
//            Scanner scanner = new Scanner(System.in);
//            while(scanner.hasNext()){
//                String s = scanner.nextLine();
//                String[] ss = s.split(" ");
//                int[] data = new int[ss.length];
//                for(int i = 0; i < s.length(); i++){
//                    data [i] = Integer.valueOf(ss[i]);
//                }
//                int a = data[0];
//                int count = 0;
//                for(int i = 1; i < data.length; i++){
//                    if(data[i] == a){
//                        count++;
//                    }else if(count > 0){
//                        count--;
//                    }else{
//                        a = data[i];
//                    }
//                }
//                System.out.println(a);
//            }
//        }
//    }

// day10,Fibonacci数列，给一个N，问N最少走几步（+1或-1）可以是斐波那契数
public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n;
        while(scanner.hasNext()){
            n = scanner.nextInt();
            int i = minStep(n);
            int result = Fibo(i) - n;
            int result1 = n - Fibo(i - 1);
            if(result < result1){
                System.out.println(Math.abs(result));
            }else{
                System.out.println(Math.abs(result1));
            }
        }
    }
    // 求n是第几个Fibo数
    public static int minStep(int n){
        int i;
        for(i = 0; Fibo(i) < n; i++){

        }
        return i;
    }
    public static int Fibo(int i){
        if(i == 0){
            return 0;
        }
        if(i == 1){
            return 1;
        }
        return Fibo(i - 1) + Fibo(i - 2);
    }
}
//}


