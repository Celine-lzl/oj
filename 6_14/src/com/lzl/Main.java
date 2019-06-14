package com.lzl;

import java.util.*;
public class Main{
        public static void main1(String[] args){
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNext()){
                List<Character> list = new ArrayList();
                int count = 0;
                String boss = scanner.nextLine();
                String red = scanner.nextLine();
                for(int i = 0; i < boss.length(); i++){
                    list.add(boss.charAt(i));
                }
                for(int i = 0; i < red.length(); i++){
                    for(int j = 0; j < list.size(); j++){
                        if(list.get(j) == red.charAt(i)){
                            count++;
                            list.remove(j);
                            break;
                        }
                    }
                }
                if(count == red.length()){
                    System.out.println("Yes"+" "+(boss.length()-count));
                }else{
                    System.out.println("No"+" "+(red.length()-count));
                }
            }
        }


    /*
    *  d24_1 小易的升级之路*/
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int n = scanner.nextInt();
            int c = scanner.nextInt();
            int[] b = new int[n];
            for(int i = 0; i < n; i++){
                b[i] = scanner.nextInt();
                if(c >= b[i]){
                    c += b[i];
                }else{
                    c += gcd(b[i],c);
                }
            }
            System.out.println(c);
        }
    }
    public static int gcd(int a, int b){
        while(a % b != 0){
            int c = a % b;
            a = b;
            b = c;
        }
        return b;
    }
//*************************************************************

    }


