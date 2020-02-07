package day11_and_12;

import java.util.*;
public class Solution {
    public static void main(String[] args) {
//        System.out.println(isPalindrome("0P"));
        char[] data = new char[]{'a','b','b','b','c','c'};
         //System.out.println(compress(data));
    }

    /**
     * 581 最短无序连续子数组
     */
    // 对原数组排序后，将排序后的数组与原数组比较，利用两个指针遍历
    // 分别是从前往后两个数组第一个不同的和从后往前第一个不同的元素
    // 则这两个区间内的元素就是需要排序的，要考虑特殊情况，比如数组原本就有序
    public int findUnsortedSubarray1(int[] nums) {
        int[] data = new int[nums.length];
        int index = 0;
        boolean flag = true;
        for(int i : nums){
            data[index++] = i;
        }
        Arrays.sort(data);
        int i = 0;
        for( ; i < data.length; i++){
            if(data[i] != nums[i]){
                flag = false;
                break;
            }
        }
        int j = nums.length-1;
        for( ; j >= 0; j--){
            if(data[j] != nums[j]){
                break;
            }
        }
        if(flag){
            return 0;
        }
        return j-i+1;
    }

    /**
     * 150 逆波兰表达式求值
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (s.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (s.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            } else if (s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (s.equals("/")) {
                int num1 = stack.pop();
                stack.push(stack.pop() / num1);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }


    /**
     * 443 压缩字符串
     */
    public int compress(char[] chars) {
        int write = 0; // 标志当前写入位置
        int dex = 0; // 下一个不同的字符的位置
        for(int read = 0; read < chars.length; read++){
            if(read == chars.length-1 || chars[read] != chars[read+1]){
                chars[write++] = chars[read];
                if(read > dex){ // 计数
                    for(char c : (""+(read-dex+1)).toCharArray()){
                        chars[write++] = c;
                    }
                }
                dex = read+1;
            }
        }
        return write;
    }


    /**
     * 125 验证回文串
     */
    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length()-1;
        s = s.toLowerCase();
        while(l < r){ // 最后剩一个时不用判断，不影响结果，因此=可用可不用
            while(l < r && !isLetter(s.charAt(l))){
                l++;
            }
            while(l < r && !isLetter(s.charAt(r))){
                r--;
            }
            if(s.charAt(l) != s.charAt(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public static boolean isPalindrome2(String s) {
        if(s == null){
            return true;
        }
        s = s.toLowerCase();
        int left = 0;
        int right = s.length()-1;
        while(left < right && right >= 0){
            char l = s.charAt(left);
            char r = s.charAt(right);
            if(!isLetter(l)){
                left++;
                continue;
            }else if(!isLetter(r)){
                right--;
                continue;
            }else{
                if(l == r){ //二者都是字母
                    left++;
                    right--;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    // 是否是字母
    public static boolean isLetter(char c){
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }
}
