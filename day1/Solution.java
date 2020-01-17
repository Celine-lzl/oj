package day1;

import java.util.*;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
public class Solution {
    public static void main(String[] args) {
        int[][] arr = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        System.out.println(Find(7,arr));
        
    }

    /**
     * 为保证出栈序列正确，在stack2内为空时，把stack1内所有元素压入stack2中
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack2.size() == 0){
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /**
     * 递归法，在于分离出左右子树
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
        if(pre.length == 1){
            return new TreeNode(pre[0]);
        }
        TreeNode root = new TreeNode(pre[0]);
        int index = 0;
        for(index = 0; index < in.length; index++){
            if(in[index] == pre[0]){
                break;
            }
        }
        // 分割出来左子树的前序中序数组和右子数的前序中序数组
        root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, index+1),Arrays.copyOfRange(in, 0, index));
        root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, index+1, pre.length),Arrays.copyOfRange(in, index+1, pre.length));
        return root;
    }

    /**
     * 或者先把链表逆置，再把逆置后的链表中的元素一个个添加到list中
     * 也可以先把链表中的元素一个个放入栈中，再一个个出栈添加到list中
     * 或者直接先把链表中每个节点值放入list中，再利用Collections.reverse()，把list内元素逆置
     */
    class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
            ArrayList<Integer> list = new ArrayList();
            if(listNode == null){
                return list;
            }
            ListNode cur = listNode;
            ListNode pre = null;
            while(cur != null){
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                list.add(pre.val);
                cur = next;
            }
            Collections.reverse(list);
            return list;
    }

    /**
     * 这样比 遍历原字符串，不是空格，添加该字符，是空格添加"%20" 效率更高
     */
    public String replaceSpace(StringBuffer str) {
        return str.toString().replaceAll(" ","%20");
    }

    /**
     * 只考虑数组中一个位置的元素，大了，舍弃该列，小了，舍弃该行，总会找到，除非不存在
     */
    public static boolean Find(int target, int [][] array) {
        if(array.length == 0){
            return false;
        }
        int row = array.length;
        int col = array[0].length;
        int x = 0;
        int y = col-1;
        // 不管别的，只比较大小，比目标值大了，就舍弃这一列
        // 比目标值小，就舍弃当前行
        while(x < row && y >= 0){
            if(target == array[x][y]){
                return true;
            }else if(target > array[x][y]){
                x++;
            }else{
                y--;
            }
        }
        return false;
    }
}
