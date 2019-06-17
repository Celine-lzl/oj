package com.lzl;
// 二叉树的最大深度
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

class Solution {
    // 报数
        public String countAndSay(int n) {
            if(n == 1) {
                return "1";
            } else if(n == 2) {
                return "11";
            }
            String lastResult = countAndSay(n-1);
            String result = "";
            int count = 1;
            int j = 0;
            for (int i = 1; i < lastResult.length(); i++) {
                if(lastResult.charAt(j) == lastResult.charAt(i)) {
                    count ++;
                } else {
                    result += count + "" + lastResult.charAt(j);
                    count = 1;
                    j = i;
                }
                if(i == lastResult.length() - 1) {
                    result+= count + "" + lastResult.charAt(i);
                }
            }

            return result;
        }


    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int ll = maxDepth(root.left);
        int rl = maxDepth(root.right);
        if(ll >= rl){
            return ll+1;
        }
        else {
            return rl+1;
        }
    }
}

public class Test {
    // 宝石与石头
    public int numJewelsInStones(String J, String S) {
        int count = 0;
        for(int i = 0; i < S.length(); i++){
            for(int j = 0; j < J.length(); j++){
                if(S.charAt(i) == J.charAt(j)){
                    count++;
                    break;
                }
            }
        }
        return count;
    }
    // 两数之和
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1;j < nums.length; j++){
                if(nums[i]+nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {

    }
}
