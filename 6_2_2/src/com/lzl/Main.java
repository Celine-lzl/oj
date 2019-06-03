package com.lzl;
import java.util.*;
public class Main{


        /*
         *day 14 组个最小数
         * 先把非0的数加到一个list中
         * 不用考虑0的个数，先输出第一个非0的数
         * 再输出0，从1开始遍历list，把剩余的
         * 数输出
         **/
        public static void main3(String[] args){
            Scanner sc = new Scanner(System.in);
            while(sc.hasNext()){
                ArrayList<Integer> al = new ArrayList<>();
                int[] nums = new int[10];
                // 将0~9的个数存放在num数组中
                for(int i = 0; i < 10; i++){
                    nums[i] = sc.nextInt();
                }
                // 将1~9的值添加进list
                for(int i = 1; i < 10; i++){
                    for(int j = 0; j < nums[i]; j++){
                        al.add(i);
                    }
                }
                // 将第一个非0数输出
                System.out.print(al.get(0));
                // 输出0
                for(int i = 0; i < nums[0]; i++){
                    System.out.print(0);
                }
                for(int i = 1; i < al.size(); i++){
                    System.out.print(al.get(i));
                }
                System.out.println();
            }
        }
}
