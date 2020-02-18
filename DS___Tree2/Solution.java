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
     * 后序遍历非递归
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new ArrayList();
        if(root == null){
            return list;
        }
        TreeNode last = null; // 被完整遍历过的节点
        TreeNode cur = root;
        while(!stack.empty() || cur != null){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            // cur的左孩子为空了，后序遍历结果便是 null 右 根
            // 此时右节点有三种情况：
            //  1: 右孩子为空，则当前结点出栈，即已被完整遍历过了
            //  2：右孩子不为空，但是也已经被遍历过了，处理同上
            //  3：右孩子不为空，且没被遍历过，则cur = cur.right
            TreeNode top = stack.peek();
            if(top.right == null){
                list.add(top.val);
                last = top;
                stack.pop();

            }else if(top.right == last){
                list.add(top.val);
                last = top;
                stack.pop();
            }else{
                cur = top.right;
            }
        }
        return list;
    }

    /**
     * 中序遍历非递归
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        Stack<TreeNode> stack = new Stack();
        TreeNode cur = root;
        if(root == null){
            return list;
        }
        while(!stack.empty() || cur != null){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode top = stack.pop();
            list.add(top.val);
            cur = top.right;
        }
        return list;
    }


    /**
     * 前序遍历非递归：循环+栈
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack();
        TreeNode cur = root;
        while(cur != null || !stack.empty()){
            while(cur != null){
                list.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode top = stack.pop();
            cur = top.right;
        }
        return list;
    }

    /**
     * 前序中序构造二叉树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int i = 0;
        for( ; i < inorder.length; i++){
            if(inorder[i] == preorder[0]){
                break;
            }
        }
        int[] leftPre = Arrays.copyOfRange(preorder, 1, i+1);
        int[] leftIn = Arrays.copyOfRange(inorder, 0, i);
        int[] rightPre = Arrays.copyOfRange(preorder, 1+leftPre.length, preorder.length);
        int[] rightIn = Arrays.copyOfRange(inorder, 1+leftPre.length, preorder.length);
        root.left = buildTree(leftPre, leftIn);
        root.right = buildTree(rightPre, rightIn);
        return root;
    }

    /**
     * 最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果p，q中有一个是根节点
        if(p == root || q == root){
            return root;
        }
        boolean pInLeft = find(root.left, p);
        boolean qInLeft = find(root.left, q);
        // p，q在同一个子树上
        if(pInLeft && qInLeft){
            return lowestCommonAncestor(root.left, p, q);
        }
        if(!pInLeft && !qInLeft){
            return lowestCommonAncestor(root.right, p, q);
        }
        // 如果p，q在两棵不同的子树上，则返回root
        return root;
    }
    public boolean find(TreeNode root, TreeNode p){
        if(root == null){
            return false;
        }
        if(root == p){
            return true;
        }
        if(find(root.left, p)){
            return true;
        }
        return find(root.right, p);
    }

    /**
     * 二叉搜索树转为有序链表
     *  分为两步，第一步是把每一个节点转换成双向链表
     *      为了保证有序，我们对二叉搜索树进行中序遍历，保证我们拿出节点的时候是有序的
     *      接下来就只需要把这些有序的节点转成双向链表即可
     *
     */
    TreeNode pre = null;
    TreeNode head = null;  // 返回值
    public TreeNode Convert(TreeNode pRootOfTree) {
        pre = null;
        head = null;
        inOrder(pRootOfTree);
        return head;
    }
    // 中序，因为要保证有序
    public void inOrder(TreeNode root){
        if(root != null){
            inOrder(root.left);
            buildList(root);
            inOrder(root.right);
        }
    }
    // 构建排序双向链表
    public void buildList(TreeNode cur){
        cur.left = pre;
        if(pre != null){
            pre.right = cur;
        }else{
            head = cur;
        }
        pre = cur;
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
