package DS___Tree2;

import java.util.*;
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

public class Solution {
    public static void main(String[] args) {

    }


    /**
     * 二叉树的层序遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList();
        if(root == null){
            return list;
        }
        // 记录当前结点在第几层
        class NodeLevel{
            TreeNode node;
            int level;
            public NodeLevel(TreeNode node, int level){
                this.node = node;
                this.level = level;
            }
        }
        LinkedList<NodeLevel> queue = new LinkedList();
        queue.add(new NodeLevel(root,0));
        while(!queue.isEmpty()){
            NodeLevel front = queue.pollFirst();
            TreeNode node = front.node;
            int level = front.level;
            if(level == list.size()){
                list.add(new ArrayList());
            }
            list.get(level).add(node.val);
            if(node.left != null){
                queue.addLast(new NodeLevel(node.left, level+1));
            }
            if(node.right != null){
                queue.addLast(new NodeLevel(node.right, level+1));
            }
        }
        return list;
    }

    /**
     * 二叉树创建字符串
     */
    public String tree2str(TreeNode t) {
        if(t == null){
            return "";
        }
        StringBuilder sbb = preOrder(t);
        return sbb.toString().substring(1,sb.length()-1);
    }
    StringBuilder sb = new StringBuilder();
    public StringBuilder preOrder(TreeNode s){
        if(s != null){
            sb.append("(");
            sb.append(s.val);
            if(s.left == null && s.right != null){
                sb.append("()");
            }else{
                preOrder(s.left);
            }
            preOrder(s.right);
            sb.append(")");
        }
        return sb;
    }

    // 利用栈和集合
    public String tree2str3(TreeNode t) {
        if(t == null){
            return "";
        }
        Stack<TreeNode> stack = new Stack();
        Set<TreeNode> set = new HashSet();
        StringBuilder sb = new StringBuilder();
        stack.push(t);
        while(!stack.empty()){
            TreeNode tmp = stack.peek();
            if(set.contains(tmp)){
                stack.pop();
                sb.append(")");
            }else{
                set.add(t);
                sb.append("(");
                sb.append(tmp.val);
                if(t.left == null && t.right != null){
                    sb.append("()");
                }
                // 先把右孩子放入栈中，保证出来的时候左子树在前
                if(tmp.right != null){
                    stack.push(tmp.right);
                }
                if(tmp.left != null){
                    stack.push(tmp.left);
                }
            }
        }
        return sb.toString().substring(1,sb.length()-1);
    }

    // 递归创建
    public String tree2str2(TreeNode t) {
        if(t == null){
            return "";
        }
        if(t.left == null && t.right == null){
            return "" + t.val;
        }
        if(t.right == null){
            return "" + t.val + "(" + tree2str2(t.left) + ")";
        }
        if(t.left == null){
            return "" + t.val + "()" + "(" + tree2str2(t.right) + ")";

        }
        return  t.val + "(" + tree2str2(t.left) + ")" + "(" + tree2str2(t.right) + ")";
    }

    /**
     * 二叉树遍历：把字符串转成书再中序遍历
     */
    static class Node{
        char val;
        Node left;
        Node right;
        Node(char val){
            this.val = val;
        }
    }
    // 返回值，返回树的根节点以及树的节点数
    static class RV{
        Node returnRoot;
        int used;
        RV(Node returnRoot, int used){
            this.returnRoot = returnRoot;
            this.used = used;
        }
    }
    public static void main1(String[] args) {
        // 可以分为两步，首先构建出二叉树，其次对构建出的二叉树进行中序遍历
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            RV root = buildtree(s);
            midorrder(root.returnRoot);
        }
    }
    // 构建树
    public static RV buildtree(String s){
        if(s == null){
            return null;
        }
        char[] tree = s.toCharArray();
        if(tree.length == 0){
            return new RV(null,0);
        }
        if(tree[0] == '#'){
            return new RV(null,1);
        }
        Node root = new Node(tree[0]);
        // 处理左子树
        char[] leftpre = Arrays.copyOfRange(tree, 1, tree.length);
        RV left = buildtree(String.valueOf(leftpre));
        // 右子树
        char[] rightpre = Arrays.copyOfRange(tree, 1+left.used, tree.length);
        RV right = buildtree(String.valueOf(rightpre));
        // 合并
        root.left = left.returnRoot;
        root.right = right.returnRoot;
        return new RV(root, 1 + left.used + right.used);
    }
    // 中序遍历
    public static void midorrder(Node root){
        if(root != null){
            if(root.left != null){
                midorrder(root.left);
            }
            System.out.print(root.val+" ");
            if(root.right != null){
                midorrder(root.right);
            }
        }
    }


    // 利用该字符串顺序和中序遍历的规律，利用栈实现
    public void treeeach(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.next();
            char[] data = s.toCharArray();
            Stack<Character> stack = new Stack();
            stack.push(data[0]);
            while(!stack.empty()){
                for(int i = 1; i < data.length-1; i++){
                    if(data[i] == '#'){
                        System.out.print(stack.pop()+" ");
                    }else{
                        stack.push(data[i]);
                    }
                }
                System.out.println();
            }
        }
    }
}
