package com.lzl;
import java.io.Serializable;
import java.util.*;
// Day 01，插入字符串变成回文
//public class Main{
//    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入字符串A");
//        String stra = scanner.nextLine();
//        System.out.println("请输入字符串B");
//        String strb = scanner.nextLine();
//        System.out.println(huiwenWays(stra,strb));
//    }
//    public static boolean isHuiwen(String a){
//        int i = 0;
//        int j = a.length()-1;
//        while( i <= j && a.charAt(i) != a.charAt(j)){
//            return false;
//        }
//        i++;
//        j--;
//        return true;
//    }
//    public static int huiwenWays(String stra,String strb){
//        int count = 0;
//        for(int i = 0; i <= stra.length(); i++){
//            StringBuffer sb = new StringBuffer(stra);
//            sb.insert(i,strb);
//            if(isHuiwen(sb.toString())){
//                count++;
//            }
//        }
//        return count;
//    }
//}

// Day2 汽水瓶子换汽水
import java.util.Scanner;
//public class Main{
//    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        int n = 0;
//        while(scanner.hasNext()){
//            n = scanner.nextInt();
//            System.out.println(drink(n));
//        }
//    }
//    public static int drink(int n){
//        int count = 0;
//        while(n > 2){
//            count += n/3;
//            n = n/3 + n % 3;
//        }
//        if(n == 2){
//            count++;
//        }
//        return count;
//    }
//}

// day03 合法的括号串
//public class Main {
//    public boolean chkParenthesis(String A, int n) {
//        // write code here
//        Stack<Character> stack = new Stack();
//        for(int i = 0; i < n; i++){
//            if(A.charAt(i) == '('){
//                stack.push(A.charAt(i));
//            }else if(A.charAt(i) == ')'){
//                if(stack.empty()){
//                    return false;
//                }else{
//                    stack.pop();
//                }
//            }else{
//                return false;
//            }
//        }
//        // 上面遍历完了后，栈里仍有元素，即没有相应的右括号与左括号匹配
//        if(!stack.empty()){
//            return true;
//        }else{
//            return false;
//        }
//    }
//}

// day03 字符串中连续最长数字串
import java.util.Scanner;
//public class Main{
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNext()){
//            String str = sc.nextLine();
//            int count = 0; // 当前正在遍历的数字串的长度
//            int max = 0;   // 最大数字串的长度
//            int end = 0;   // 数字串中最后一个数字出现的位置
//            for(int i = 0; i < str.length(); i++){
//                if(str.charAt(i) >= '0' || str.charAt(i) <= '9'){
//                    count++;
//                    if(max < count){
//                        max = count;
//                        end = i;
//                    }
//                }else{
//                    count = 0;
//                }
//            }
//            System.out.println(str.substring(end-max+1,end));
//        }
//    }
//}

// day4 删除A字符串中与B字符串中相同的字符
import java.util.Scanner;
//public class Main{
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while(scanner.hasNext()){
//            String stra = scanner.nextLine();
//            String strb = scanner.nextLine();
//            String pattern = "["+strb+"]";
//            String result = stra.replaceAll(pattern,""); //正则表达式，中括号就是匹配括
//        }                                                            //号内的其中一个
//    }
//}

// day7 求两个数的最小公倍数
import java.util.Scanner;
//public class Main{
//    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        while(scanner.hasNext()){
//            int a = scanner.nextInt();
//            int b = scanner.nextInt();
//            int max = a;
//            int min = b;
//            if(a < b){
//                max = b;
//                min = a;
//            }
//            int r = Gcd(max,min);
//            System.out.println(a * b / r);
//
//        }
//    }
//    public static int Gcd(int max, int min){
//        while(max % min != 0){
//            int n = max % min;
//            max = min;
//            min = n;
//            n = max % min;
//        }
//        return min;
//        // 一步登天法
//        //return max % min == 0? min:Gcd(min,max%min);
//    }
//}

