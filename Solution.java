package DP;


import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    //=====================================================================================================
    // 斐波那契用数组保存中间结果，空间复杂度为O(n)
    public int Fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] res = new int[n + 1];
        res[0] = 0;
        res[1] = 1;
        for (int i = 2; i <= n; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n];
    }

    // 递归，时间复杂度高，保存了大量的中间结果，时间复杂度高
    public class Solution1 {
        public int Fibonacci(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 1;
            }
            return Fibonacci(n - 1) + Fibonacci(n - 2);
        }
    }

    // 只用临时变量存取所需的前两个数的斐波那契
    public int Fibonacci2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int a1 = 0;
        int a2 = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = a1 + a2;
            a1 = a2;
            a2 = res;
        }
        return res;
    }
    //===================================================================================================

    /**
     * 变态跳台阶
     */
    // 定义状态F(n)：表示还剩下n级台阶时的跳法数
    // 第一步若跳1阶，则剩下有F(n-1)
    //          2阶，       F(n-2)
    //          3阶，       F(n-3)
    //则F(n) = F(n-1) + F(n-2) + F(n-3) + ... + F(0)
    // F(n-1) = F(n-2) + F(n-3) + ... + F(0)
    // 那F(n) = 2 * F(n-1);
    // 等比数列：F(1) = 1      F(2) = 2     F(3) = 4    F(4) = 8 ... F(n) = 2 ^ (n-1)
    public int JumpFloorII(int target) {
        if (target <= 0) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        int[] res = new int[target + 1];
        res[0] = 0;
        res[1] = 1;
        for (int i = 2; i <= target; i++) {
            res[i] = 2 * res[i - 1];
        }
        return res[target];
        // 上述开辟数组，空间复杂度为O(n)，也可以只使用一个临时变量
//        int temp = 1;
//        // 1、2、4、8
//        for(int i = 2; i <= target; i++){
//            temp = 2 * temp;
//        }
//        return temp;
    }

    // 乘法+循环效率不高，可发现这是个等比数列，利用等比数列通项公式
    public int JumpFloorII2(int target) {
        if (target <= 0) {
            return 0;
        }
        return 1 << (target - 1);
    }
// =======================================================================================
    /**
     *连续子数组的最大和
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if(array.length <= 0){
            return -1;
        }
        int maxN = array[0];
        int sum = 0;
        // 状态：F(i)为以a[i]结尾的连续字数组最大和
        // F(i) = Math.max(F(i-1)+a[i],a[i])
        for(int i = 0; i < array.length; i++){
            // 如果sum小于0，就舍弃前面所有
            // 如果array[i]小于0，就不要他，从他的下一个再开始
            sum = Math.max(sum + array[i],array[i]);
            maxN = Math.max(sum,maxN);
        }
        return maxN;
    }





















}
