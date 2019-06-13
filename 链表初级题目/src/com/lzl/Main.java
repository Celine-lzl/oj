package com.lzl;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main{
//    /*
//    * d22_1 到底买不买
//    * */
//    public static void main1(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        while(scanner.hasNext()){
//            List<Character> list = new ArrayList();
//            int count = 0;
//            String boss = scanner.nextLine();
//            String red = scanner.nextLine();
//            for(int i = 0; i < boss.length(); i++){
//                list.add(boss.charAt(i));
//            }
//            for(int i = 0; i < red.length(); i++){
//                for(int j = 0; j < list.size(); j++){
//                    if(list.get(j) == red.charAt(i)){
//                        count++;
//                        list.remove(j);
//                        break;
//                    }
//                }
//            }
//            if(count == red.length()){
//                System.out.println("Yes"+" "+(boss.length()-count));
//            }else{
//                System.out.println("No"+" "+(red.length()-count));
//            }
//        }
//    }
//    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        while(scanner.hasNext()){
//            String boss = scanner.nextLine();
//            String red = scanner.nextLine();
//            char[] data = new char[boss.length()];
//            for(int i = 0; i < boss.length(); i++){
//                data[i] = boss.charAt(i);
//            }
//            int lessCount = 0; // 缺少的珠子数
//            for(int i = 0; i < red.length(); i++) {
//                int count = 0; // 存在的珠子数
//                for (int j = 0; j < data.length; j++) {
//                    if (red.charAt(i) == boss.charAt(j) && data[j] != ' ') {
//                        data[j] = ' ';
//                        count++;
//                        break;
//                    }
//                }
//                if(count == 0){
//                    lessCount++;
//                }
//            }
//            if(lessCount == 0){
//                System.out.println("Yes" + " "+(boss.length() - red.length()));
//            }else{
//                System.out.println("NO" + " "+lessCount);
//            }
//        }
//    }
//}

