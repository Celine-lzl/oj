package com.lzl;

import java.util.Scanner;
import java.util.Stack;
//  **************************Day03 给定一个字符串，判断其最大连续数字串的长度***********************
//public class Test{
//    public static void main(String[] args) {
//        Scanner in =new Scanner(System.in);
//        int maxLength=0;//记录最长的数字的串的长度
//        int count=0;//记录连续数字的个数
//        int end=0;//记录数字结束的位置
//        String s=null;
//        while(in.hasNext()){
//            s=in.nextLine();
//            for(int i=0;i<s.length();++i){
//                if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
//                    count++;
//                    if(count>maxLength){
//                        end=i;
//                        maxLength=count;
//                    }
//                }else{
//                    count=0;
//                }
//            }
//        }
//        System.out.println(s.substring(end-maxLength+1,end+1));
//    }
//}

//  **************************Day03 给定一个字符串，判断其最大连续数字串的长度***********************
//public class Test {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String string = scanner.nextLine();
//        String ressult = "";
//        int count = 0;
//        char [] arr = string.toCharArray();
//        for (int i= 0 ;i<arr.length;i++){
//            if('0'<=arr[i] && '9'>= arr[i]){//当前的是数字
//                count = 1;//初始化计算器
//                int index = i;//在后面的循环存储截至索引
//                for(int j=i+1;j<arr.length;j++){
//                    if('0'<=arr[j] && '9'>= arr[j]){
//                        count++;
//                        index =j;
//                    }else {
//                        break;
//                    }
//                }if(count>ressult.length()){
//                    ressult = string.substring(i,index+1);
//                }
//            }else {
//                continue;
//            }
//
//        }
//        System.out.println(ressult);
//    }
//}

// *************************  给定一个字符串，判断其是否为合法括号串 ******************************
public class Test {
    public static boolean chkParenthesis(String A, int n) {
        Stack<Character> stack = new Stack<>();
        char[] chars = A.toCharArray();
        for (int i = 0; i < n; i++) {
            // 是左括号，入栈
            if (chars[i] == '(') {
                stack.push(chars[i]);
            } else if (chars[i] == ')') {
                // 是右括号，分情况讨论，如果栈深不为0，且栈顶元素为左括号，则左括号出栈
                if (stack.size() > 0) {
                    if (stack.peek() == '(') {
                        stack.pop();
                    }
                } else { // 栈为空或栈顶元素不为左括号，返回
                    return false;
                }
            } else {
                return false;
            }
        }
        // 所有的左括号都出栈了，则为true
        return stack.size() == 0;
    }
    public static void main(String[] args) {
        System.out.println("请输入");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(chkParenthesis(s,s.length()));
    }
}