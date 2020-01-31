package day5_and_6;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

    }

    /**
     * 917，仅仅反转字母
     * 用双指针，一个在前一个在后，如果前后都是字母，交换二者位置
     * 只有一边是字母，那边的指针前移或者后移
     */
    public String reverseOnlyLetters(String S) {
        if(S == null){
            return null;
        }
        char[] data = S.toCharArray();
        int left = 0;
        int right = data.length-1;
        while(left < right){
            if(isLetter(data[left]) && isLetter(data[right])){
                swap(data,left,right);
                left++;
                right--;
            }else if(!isLetter(data[left])){
                left++;
            }else if(!isLetter(data[right])){
                right--;
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < data.length; i++){
            sb.append(data[i]);
        }
        return sb.toString();
    }
    public void swap(char[] data, int x, int y){
        char tmp = data[x];
        data[x] = data[y];
        data[y] = tmp;
    }
    public boolean isLetter(char c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    // 利用栈，是字母，则压栈进去
    public String reverseOnlyLetters2(String S) {
        Stack<Character> stack = new Stack();
        char[] data = S.toCharArray();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < S.length(); i++){
            if(isLetter(data[i])){
                stack.push(data[i]);
            }
        }
        for(int i = 0; i < S.length(); i++){
            if(!isLetter(data[i])){
                sb.append(data[i]);
            }else{
                sb.append(stack.pop());
            }
        }
        return sb.toString();
    }

    /**
     * 977，有序数组的平方
     * 利用有序数组的性质，找到正负数的分界处，开始比较
     * 每次比较将较小的那个放到结果数组中，最后把剩余的再填进去
     * 也可以先把数组内元素平方求出来，再对其进行排序
     */
    public static int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];
        // 数组内全是正数
        if(A[0] >= 0){
            for(int i = 0; i < A.length; i++){
                res[i] = A[i] * A[i];
            }
        }
        // 数组内全是负数
        if(A[A.length-1] < 0){
            for(int i = 0; i < A.length; i++){
                res[i] = A[A.length-1-i] * A[A.length-1-i];
            }
        }
        // 有正有负时
        int left = 0; //最小负数的下标
        int right = 0; //最小整数的下标
        while(left < A.length && A[left] < 0){
            left++;
        }
        right = left;
        left -= 1;
        int index = 0;
        while(left >= 0 && right < A.length){
            if(A[left] * A[left] < A[right] * A[right]){
                res[index++] = A[left] * A[left];
                left--;
            }else{
                res[index++] = A[right] * A[right];
                right++;
            }
        }
        if(left < 0){
            for(int i = right; i < A.length; i++){
                res[index++] = A[i] * A[i];
            }
        }
        if(right == A.length){
            for(int i = left; i >= 0; i--){
                res[index++] = A[i] * A[i];
            }
        }
        return res;
    }

    /**
     * 925，长按键入
     * 此处不可以用linkedhashmap来存储模块，比如kikcx 这种，第二个k会覆盖掉第一个k
     * 可以像题解一样，创造一个类，类里面包含两个参数，一个统计字符，一个统计相应字符的个数
     */
    public boolean isLongPressedName(String name, String typed) {
        Group name1 = group(name);
        Group typed1 = group(typed);
        if (!name1.key.equals(typed1.key)) {
            return false;
        }
        for (int i = 0; i < name1.key.length(); i++) {
            if (name1.count.get(i) > typed1.count.get(i)) {
                return false;
            }
        }
        return true;
    }

    public Group group(String s) {
        StringBuffer key = new StringBuffer();
        List<Integer> count = new ArrayList();
        int len = s.length();
        // 定位字符串中每一个模块的起始地址
        // 如allexx 可分成 a ll e xx 四个模块
        int index = 0;
        for (int i = 0; i < len; i++) {
            if (i == len - 1 || s.charAt(i) != s.charAt(i + 1)) {
                key.append(s.charAt(i));
                count.add(i - index + 1);
                index = i + 1;
            }
        }
        return new Group(key.toString(), count);
    }
}
    class Group{
        String key;
        List<Integer> count;
        Group(String k, List<Integer> c){
            key = k;
            count = c;
        }


    // 利用两个指针，分别指向两个字符串的首部
    // 从前往后遍历，遇到不符合的直接返回false
    public  static boolean isLongPressedName1(String name, String typed) {
        int i = 0;
        int j = 0;
        // 这里如果不判断直接走下面，下标会减成-1
        if(name.charAt(0) != typed.charAt(0)){
            return false;
        }
        while(i < name.length() && j < typed.length()){
            if(name.charAt(i) == typed.charAt(j)){
                i++;
                j++;
            }else if(name.charAt(i) != typed.charAt(j) &&
                    typed.charAt(j) == typed.charAt(j-1)){
                j++;
            }else if(name.charAt(i) != typed.charAt(j) &&
                    typed.charAt(j) != typed.charAt(j-1)){
                return false;
            }
        }
        // 指向typed的指针已经走完了，name却还没走完
        if(i < name.length()){
            return false;
        }
        return true;
    }
    /**
     * 21，存在重复元素
     * 利用set去重的性质，将所有元素都保存在set中
     * 最后若set大小和nums大小相同，则证明没有重复元素
     * 也可以用set.contains方法判断，每次往set内添加元素时判断一下是否含有
     * 若有，则直接返回true，没有则添加进去
     **/
    public boolean containsDuplicate(int[] nums) {
        if(nums.length == 0 || nums == null){
            return false;
        }
        Set<Integer> set = new HashSet();
        for(int i : nums){
            set.add(i);
        }
        return nums.length != set.size();
    }

    // 利用set的contains方法
    public boolean containsDuplicate2(int[] nums) {
        if(nums.length == 0 || nums == null){
            return false;
        }
        Set<Integer> set = new HashSet();
        for(int i : nums){
            if(set.contains(i)){
                return true;
            }
            set.add(i);
        }
        return false;
    }

    // 先把数组排序，再遍历一遍
    public boolean containsDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-1; i++){
            if(nums[i] == nums[i+1]){
                return true;
            }
        }
        return false;
    }
}
