package com.lzl;

import java.math.BigInteger;
import java.util.*;

public class Main{
    /**
     * d30_1 奇偶校验
     * 将输入的数当字符处理，字符减去'0'就是原数字
     * 进行位运算，将10进制数字转为二进制数
     * 统计二进制中1的个数，为偶数的，将第一位变为1
     */
    public static void main6(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String str = in.nextLine();
            jiQiaoYan(str.toCharArray());//将输入的字符串统一转换为字符数组
        }
    }

    public static void jiQiaoYan(char[] s){
        int[] result = new int[8];
        for(int i = 0; i < s.length; i++){
            int n = 0x01;
            int j = 7;
            int sum = 0;
            while(j > 0){    //需要进行7次与运算，得出1的个数 及 二进制形式
                result[j] = (s[i] & n) == 0 ? 0 : 1;    //与运算
                if(result[j] == 1) sum++;    //个数
                n = n << 1;
                j--;
            }

            if((sum & 1) == 0) result[0] = 1;    //进行校验
            for(int k = 0; k < result.length; k++){
                System.out.print(result[k]);
            }
            result[0] = 0;
            System.out.println();
        }
    }

    /**
     * d30_2 大整数排序
     * 不直接自己排序了，利用Arrays.sort()
     */
    public static void main2(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextBigInteger()) {
            int n = sc.nextInt();
            BigInteger[] big = new BigInteger[n];
            for(int i = 0; i < n; i++){
                big[i] = sc.nextBigInteger();
            }
            Arrays.sort(big);
            for(int i = 0; i < n; i++){
                System.out.println(big[i]);
            }
        }
    }

    /**
     * d34_2 球的半径和体积
     * pi = arccos(-1)在java中表示为Math.acos(-1)
     * arcsinx = Math.asin(x) 其他同理
     */
    public static void main1(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int x0 = sc.nextInt();
            int y0 = sc.nextInt();
            int z0 = sc.nextInt();
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int z1 = sc.nextInt();
            double pi = Math.acos(-1);
            double r = Math.sqrt((x0-x1)*(x0-x1)+(y0-y1)*(y0-y1)+(z0-z1)*(z0-z1));
            double v = 4*pi*r*r*r/3;
            System.out.print(String.format("%.3f",r)+" "+String.format("%.3f",v));
        }
    }

    /**
     * d34_1 Broken Keyboard
     * 遍历实际输出的字符串，把没有在实际输出字符串中包含的应该输出的字符
     * 添加到一个list数组中，但是由于list会重复添加，因此需要去重处理，
     * 若是直接用set，不能满足字母输入的顺序。
     * 从输出用例可以看出，结果忽略了大小写，因此将小写字母转为大写字母
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String org = sc.next();
            String act = sc.next();
            List<Character> list = new ArrayList<>();
            for(int i = 0; i < org.length(); i++){
                char a = org.charAt(i);
                if (!act.contains(String.valueOf(a))) {
                    // 题意忽略了大小写
                    if(a >= 'a' && a <= 'z'){
                        a -= 32;
                    }
                    list.add(a);
                }
            }
            // 对list去重
            for(int i = 0; i < list.size()-1; i++){
                for(int j = list.size()-1; j > i; j--){
                    if(list.get(j).toString().equals(list.get(i).toString())){
                        list.remove(j);
                    }
                }
            }
            // 输出
            for(int i = 0; i < list.size(); i++){
                System.out.print(list.get(i));
            }
        }
    }
}