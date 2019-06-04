package com.lzl;

import java.math.BigInteger;
import java.util.*;

public class Main{

    /*
    * d13_1 跟奥巴马一起编程
    * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String c = sc.next();
        // 先输出第一行
        for(int i = 0; i < N; i++){
            System.out.print(c);
        }
        // 换行
        System.out.println();
        // 输出中间行的第一列
        // 中间行的行数为(Math.ceil(col/2))-2
        for(int i = 0; i < Math.ceil((double)N/2)-2; i++){
            // 输出第一列
            System.out.print(c);
            // 中间的空格列
            for(int j = 0; j <= N-3; j++){
                System.out.print(" ");
            }
            // 最后一列
            System.out.println(c);
        }
        // 输出最后一行
        for(int i = 0; i < N; i++){
            System.out.print(c);
        }
    }

    /*
    * d13_2
    * 超长正整数相加
    * 有个BigInteger类，注意是类，里面有加减乘除等各种方法可实现大数据运算
    * 使用时记得导包，java.math.BigInteger
    * */
    public static void Bigadd(){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String a = scanner.nextLine();
            String b = scanner.nextLine();
            BigInteger s1 = new BigInteger(a);
            BigInteger s2 = new BigInteger(b);
            System.out.println(s1.add(s2));
        }
    }


    /*
    * d14_1 组个最小数
    * */
    public static void main1(String[] args){
        Scanner scanner = new Scanner(System.in);
        int[] data = new int[10];
        while(scanner.hasNext()){
            // 将0~9每个数字各有多少个先保存起来
            for(int i = 0; i < 10; i++){
                data[i] = scanner.nextInt();
            }
            ArrayList<Integer> list = new ArrayList<Integer>();
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
        }
    }
    /*
     * d14_2 尼科彻斯定理
     * */
    public static void NikeChese(String[] args){
        Scanner scanner = new Scanner(System.in);
        int m;
        while(scanner.hasNext()){
            m = scanner.nextInt();
            StringBuffer res = new StringBuffer();
            // 等差数列求和先算出第一项
            int start = m*(m-1)+1;
            for(int i = 0; i < m; i++){
                if( i < m-1){
                    res.append(start+"+");
                    start += 2;
                }else{
                    res.append(start+" ");
                }
            }
            System.out.println(res.toString());
        }
    }


    /*
    *  d15_2 饥饿的小易
    * */
    public static void hungry(String[] args){
        Scanner scanner = new Scanner(System.in);
        int x_0 = scanner.nextInt();
        // 8x+7就是走三次2x+1,4x+3就是走两次2x+1
        // 因此我们只需要看走了多少次2x+1后模1000000007是0
        // 如果次数是3（2x+1）的整数倍，返回x_0/3(2x+1)
        // 否则返回int res = count/3+1
        //(count+2)/3等价于int res = count%3 == 0?count/3:count/3 + 1
        scanner.close();
        int count = 0; // 走了多少次2x+1
        while(x_0 != 0 && count <= 300000){
            x_0 = ((x_0 << 1) + 1) % 1000000007;
            count++;
        }
        int res = (count+2)/3;
        System.out.println(res > 100000 ? -1 : res);
    }
}
