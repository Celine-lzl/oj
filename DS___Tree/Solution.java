package DS___Tree;
import java.util.*;

  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

public class Solution {

    /**
     * 对称二叉树
     * 迭代法
     */
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        if(root == null){
            return true;
        }
        queue.add(root);
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if(t1 == null && t2 == null){
                continue;
            }
            if(t1 == null || t2 == null){
                return false;
            }
            if(t1.val != t2.val){
                return false;
            }
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }

    // 递归版本
    public boolean isSymmetric(TreeNode root) {
        if(root == null || root.left == null && root.right == null){
            return true;
        }
        if(root.left == null || root.right == null){
            return false;
        }
        return isSameTree(root.left,root.right);
    }
    public boolean isSameTree2(TreeNode s, TreeNode t){
        if(s == null && t == null){
            return true;
        }
        if(s == null || t == null){
            return false;
        }
        return s.val == t.val && isSameTree(s.left,t.right) && isSameTree(s.right,t.left);
    }

    /**
     * 平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null || root.left == null && root.right == null){
            return true;
        }
        TreeNode cur = root;
        int left = getLength(cur.left);
        int right = getLength(cur.right);
        int diff = left - right;
        if(diff > 1 || diff < -1){
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        return 1 + Math.max(getLength(root.left),getLength(root.right));
    }
    public int getLength(TreeNode cur){
        if(cur == null){
            return 0;
        }
        if(cur.left == null && cur.right == null){
            return 1;
        }
        return 1 + Math.max(getLength(cur.left),getLength(cur.right));
    }

    /**
     *  另一个树的子数
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null && t == null){
            return true;
        }
        if(s == null || t == null){
            return false;
        }
        return  s.val == t.val &&isSameTree(s.left,t.left) && isSameTree(s.right,t.right) ||
                isSubtree(s.left,t) || isSubtree(s.right,t);
    }

    /**
     * 二叉树的前序遍历递归版本
      */
    List<Integer> list = new ArrayList();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null){
            return list;
        }
        TreeNode cur = root;
        list.add(cur.val);
        if(root.left != null){
            preorderTraversal(root.left);
        }
        if(root.right != null){
            preorderTraversal(root.right);
        }
        return list;
    }

    /**
     * 相同的树
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }else if(p == null || q == null){
            return false;
        }
        if(p.val != q.val){
            return false;
        }
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

    /**
     * 二叉树的中序遍历递归版本
     */
    List<Integer> list1 = new ArrayList();
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null){
            return list;
        }
        TreeNode cur = root;
        inorderTraversal(cur.left);
        list.add(cur.val);
        inorderTraversal(cur.right);
        return list;
    }

    /**
     * 二叉树的后序遍历递归版本
     */
    List<Integer> list2 = new ArrayList();
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null){
            return list;
        }
        TreeNode cur = root;
        postorderTraversal(cur.left);
        postorderTraversal(cur.right);
        list.add(cur.val);
        return list;
    }


}

