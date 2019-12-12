package jzof2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;

    }
}

public class Solution {
    public static void main(String[] args) {
        int[] data = {4,8,6,12,16,14,10};
    }

    /**
     * 二叉树中和为某一值的路径
     */
    ArrayList<ArrayList<Integer>> res = new ArrayList();
    ArrayList<Integer> list = new ArrayList(); //当前遍历的一条路径
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root == null){
            return res;
        }
        list.add(root.val);
        target -= root.val;
        if(0 == target && root.left == null && root.right == null)
            res.add(new ArrayList<Integer>(list));
        FindPath(root.left,target);
        FindPath(root.right,target);
        // 回退到上一个节点处
        list.remove(list.size()-1);
        return res;
    }

    /**
     * 二叉搜索树的后序遍历序列
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length == 0){
            return false;
        }
        return isTreeBST(sequence,0,sequence.length-1);
    }
    public boolean isTreeBST(int [] sequence,int start,int end ){
        // 空树或者只有根节点
        if(start >= end){
            return true;
        }
        int i = start;
        // 把左子树找出来
        for( ; i < end; i++){
            if(sequence[i] > sequence[end]){
                break;
            }
        }
        // 判断右子树
        for(int j = i; j < end; j++){
            if(sequence[j] < sequence[end]){
                return false;
            }
        }
        return isTreeBST(sequence,start,i-1) && isTreeBST(sequence,i,end-1);
    }


    /**
     * 从上到下打印二叉树
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<TreeNode> queue = new ArrayList<>();
        if (root == null) {
            return list;
        }
        queue.add(root);
        while (queue.size() != 0) {
            TreeNode temp = queue.remove(0);
            if (temp.left != null){
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
            list.add(temp.val);
        }
        return list;
    }

    /**
     * 栈的压入弹出序列
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        // 当是出栈序列时，那么栈顶元素与出栈顺序中的第一个元素必定相同
        Stack<Integer> stack = new Stack();
        int index = 0; // 指向出栈序列
        for(int i = 0; i < pushA.length; i++){
            stack.push(pushA[i]);
            while(!stack.empty() && stack.peek() == popA[index]){
                stack.pop();
                index++;
            }
        }
        return stack.empty();
    }

    /**
     * 包含min函数的栈
     */
    Stack<Integer> stack = new Stack();  // 数据栈
    Stack<Integer> stack1 = new Stack();   // 辅助栈
    public void push(int node) {
        stack.push(node);
        // 建立一个辅助栈，保证辅助栈栈顶存储的永远是最小值
        // 因此每次往辅助栈里压数据时，要与辅助栈栈顶元素比较
        // 如果该数据小于栈顶，则压入，如果不小于，则把栈顶元素再次压入
        if(stack1.isEmpty() || node < stack1.peek()){
            stack1.push(node);
        }else{
            stack1.push(stack1.peek());
        }
    }
    public void pop() {
        stack.pop();
        stack1.pop();
    }
    public int top() {
        return stack.peek();
    }
    public int min() {
        return stack1.peek();
    }
}