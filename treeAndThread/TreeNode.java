package com.lzl;
import java.util.*;
public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
class Solution {
    /**
     * 递归前序
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        if(root == null){
            return list;
        }else{
            list.add(root.val);
            if(root.left != null){
                list.addAll(preorderTraversal(root.left));
            }
            if(root.right != null){
                list.addAll(preorderTraversal(root.right));
            }
        }
        return list;
    }

    /**
     * 递归中序
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        if(root == null){
            return list;
        }
        if(root.left != null){
            list.addAll(inorderTraversal(root.left));
        }
        list.add(root.val);
        if(root.right != null){
            list.addAll(inorderTraversal(root.right));
        }
        return list;
    }

    /*
       递归后序
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        if(root == null){
            return list;
        }
        if(root.left != null){
            list.addAll(postorderTraversal(root.left));
        }
        if(root.right != null){
            list.addAll(postorderTraversal(root.right));
        }
        list.add(root.val);
        return list;
    }

    /**
     *  相同的树
     *  两个树相同，根节点一定相同
     *  根节点的左右孩子也相同
     *  因此可以利用递归
     */
//    public boolean isSameTree(TreeNode p, TreeNode q) {
//        if(p == null && q == null){
//            return true;
//        }else if(p == null && q != null || p != null && q == null){
//            return false;
//        }else{
//            if(p.val == q.val){
//                return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
//            }else{
//                return false;
//            }
//        }
//    }

    /**
     *  另一个树的子树
     *  不用先找到t在s中的哪个位置
     *  利用递归，如果根不是，那继续调用isSubtree，在根的左子树找
     *  左子树找到后判断是否是同一个树，如果不是，继续在右子树找
     */
        public static boolean isSubtree(TreeNode s, TreeNode t) {
            if (s == null && t == null) {
                return true;
            }
            if (s == null || t == null) {
                return false;
            }
            if (s.val == t.val) {
                if (isSameTree(s, t)) {
                    return true;
                }
            }
            if (isSubtree(s.left, t)) {
                return true;
            } else {
                return isSubtree(s.right, t);
            }
        }
        // 相同的树
        public static boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            } else if (p == null && q != null || p != null && q == null) {
                return false;
            } else {
                if (p.val == q.val) {
                    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
                } else {
                    return false;
                }
            }
        }
//        这是错误的想法，写的也是错的
//    // 节点是否在另一个树中存在
//    public static TreeNode isIn(TreeNode s,TreeNode t){
//        if(s == null && t != null || s != null && t == null){
//            return null;
//        }
//        if(s == t){
//            return s;
//        }
//        if(s.left != null && t.left != null){
//            TreeNode flagl = isIn(s.left,t);
//            if(flagl != null){
//                return flagl;
//            }

//        }
//        return isIn(s.right,t);
//    }

    /**
     * 二叉树的最大深度
     * 体会递归！！！！
     */
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }else if(root.left == null && root.right == null){
            return 1;
        }else{
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left,right)+1;
        }
    }

    /**
     * 平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        // 不是平衡树有可能是根节点的左右子树不平衡
        // 也有可能是左右子树的中有子树不平衡
        if(root == null){
            return true;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        // 左右子树中有子树不平衡
        if(left == -1 || right == -1){
            return false;
        }
        // 根的左右子树不平衡
        if(Math.abs(left-right) > 1){
            return false;
        }
        return true;
    }
    // 获取树的高度
    public int getDepth(TreeNode root) {
        if(root == null){
            return 0;
        }else if(root.left == null && root.right == null){
            return 1;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        if(left == -1 || right == -1){
            return -1;
        }
        // 只要有某个节点的子树不平衡，直接返回-1，从主方法返回false
        if(Math.abs(left-right) > 1){
            return -1;
        }
        // 正常求高度流程
        return Math.max(left,right)+1;
    }
    }
