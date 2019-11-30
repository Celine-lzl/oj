package tx1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class ListNode {
       int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}


public class Solution {
    public static void main(String[] args) {
        int[][] array = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};

    }

    /**
     * 10, 矩形覆盖
     */
    public int RectCover(int target) {
        // 大矩形只剩下2*1没覆盖时，只有1种方法覆盖剩下的
        //           2*2       ,    2
        //           2*3.......,....3
        //           2*4.......,....5
        // 发现这是个斐波那契额数列
        if(target < 1){
            return 0;
        }
        if(target == 1){
            return 1;
        }
        if(target == 2){
            return 2;
        }
        int a = 1;
        int b = 2;
        int c = 0;
        for(int i = 3; i <= target; i++){
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 9, 变态跳台阶
     * 第一次有n中选择：
     *  跳1级：剩n-1
     *    2:n-2
     *    3:n-3
     *    ...
     *    n-1:1
     *    n:0
     *    F(n) = F(n-1)+F(n-2)+F(n-3) + ... F(1) = 2*F(n-1)
     */
    public int JumpFloorII(int target) {
        if(target < 1){
            return 0;
        }
        if(target == 1){
            return 1;
        }
        // 青蛙要跳到n级的台阶，即它最后的终点是第n级台阶
        // 那么对于前面的n-1个台阶，他都有两种选择，跳或不跳
        // 2*2*2*2*2*...*2  共n-1个2相乘
        return 1 << target-1; // 1 << target-1  == 2^(n-1)
        // 方法2：推导出F(n) = 2*F(n-1);
        //return 2*JumpFloorII(target-1); 这个也可以，就是递归数量多时，可能栈溢出
    }

    /**
     * 8，跳台阶
        当n > 2时，有两种选择：
            1, 跳1级：那么剩下 target-1 级台阶
            2, 跳2级：那么剩下 target-2 级台阶
            显然，是个斐波那契数列（和斐波那契的区别在于当n == 2时，斐波那契第二项是1，这个是2）
     */
    public int JumpFloor(int target) {
        if(target < 1){
            return 0;
        }
        if(target == 1){
            return 1;
        }
        if(target == 2){
            return 2;
        }
        return JumpFloor(target-1) + JumpFloor(target-2);
    }

    /**
     * 7, 斐波那契数列
     * 递归当数量较大时(超过40)会栈溢出
     */
    public int Fibonacci(int n) {
        if(n <= 0){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        int a = 1;
        int b = 1;
        int c = 0;
        for(int i = 3; i <= n; i++){
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 6，旋转数组最小值
     * 我好像没体会到这道题的题意
     */
    public int minNumberInRotateArray(int [] array) {
        if(array.length == 0){
            return 0;
        }
        int min = array[0];
        for(int i = 1; i < array.length; i++){
            if(array[i] < min){
                min = array[i];
            }
        }
        return min;
    }


    /**
     *  5，用两个栈实现队列
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node) {
        stack1.push(node);
    }
    public int pop() {
        if(stack2.empty()){
            // 2, stack2此时为空，把stack1里的值都(while)压入stack2
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        // 1, 确定返回值。当stack2为空时，需要把stack1里的数据都压入stack2
        return stack2.pop();
    }

    /*
    *  4,重建二叉树
    */
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre == null || in == null){
            return null;
        }
        if(pre.length == 0 || in.length == 0){
            return null;
        }
        if(pre.length != in.length){
            return null;
        }
        // 仅有根节点时
        if(pre.length == 1){
            return new TreeNode(pre[0]);
        }

        TreeNode root = new TreeNode(pre[0]);  // 根节点
        int index = 0; // 根节点在中序中的位置
        for(int i = 0; i < in.length; i++){
            if(in[i] == pre[0]){
                index = i;
                break;
            }
        }
        // 构建左右子树的前序遍历和中序遍历的数组
        int[] leftPre = new int[index];
        int[] leftIn = new int[index];
        int[] rightPre = new int[pre.length-1-index];
        int[] rightIn = new int[pre.length-1-index];
        leftPre = Arrays.copyOfRange(pre, 1, index+1);
        leftIn = Arrays.copyOfRange(in, 0, index);
        rightPre = Arrays.copyOfRange(pre,1+index , pre.length);
        rightIn = Arrays.copyOfRange(in,1+index , pre.length);
        // 把根节点的左右子树拼起来
        root.left = reConstructBinaryTree(leftPre, leftIn);
        root.right = reConstructBinaryTree(rightPre, rightIn);
        return root;
    }

    /**
     * 3，从尾到头打印链表
     *  体会递归，当然也可以直接用栈或者先逆序链表，再从头打印，但这种会破坏链表原有结构(具体看题目要求)
     */
    ArrayList<Integer> list = new ArrayList();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode != null){
            if(listNode.next != null){
                printListFromTailToHead(listNode.next);
            }
            list.add(listNode.val);
        }
        return list;
    }


    /*
    *  2，替换空格
    */
      public String replaceSpace(StringBuffer str) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' '){
                sb.append("%20");
            }else{
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }


    /**
     *  1，二维数组中的查找
     */
    public boolean Find(int target, int [][] array) {
        boolean flag = false;
        int rows = array.length; // 二维数组实际的行数
        int cols = array[0].length;  // 二维数组实际的列数
        if(array.length > 0 && rows > 0 && cols > 0){  // 保证二维数组不为空
            int row = 0;  // 当前定位的区域是第几行
            int col = cols - 1;  // 当前定位的区域是第几列
            while( row < rows && col >= 0){
                if(array[row][col] == target){
                    flag = true;
                    break;
                }else if(array[row][col] > target){
                    col--;
                }else{
                    row++;
                }
            }
        }
        return flag;
    }



    /**
     *  数组中只出现一次的数字
     */
    public static void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        boolean flag = true;
        Arrays.sort(array);
        for(int i = 0; i < array.length-1; i++){
            if(array[i] == array[i+1]){
                i++;
            }else{
                if(flag){
                    flag = false;
                    num1[0] = array[i];
                    continue;
                }else{
                    num2[0] = array[i];
                }
            }
        }
        if(array[array.length-2] != array[array.length-1]){
            num2[0] = array[array.length-1];
        }
    }


}
