import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
public class Main_9_17 {
    /**
     * 把二叉树打印成多行
     */
    ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> listAll = new ArrayList();
        if(pRoot == null){
            return listAll;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while(queue.size() > 0){
            int num = 0;
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList();
            while(num++ < size){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            listAll.add(list);
        }
        return listAll;
    }

    /**
     * 二叉树的下一个节点
     * 可以先找到该节点所在这棵树的根节点
     * 有了根节点就可以做中序遍历了，将中序遍历的结果存入到一个List数组中
     * 在中序遍历中找到该节点的下一个节点
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode){
        TreeLinkNode cur = pNode;
        // 找根节点
        while(cur.next != null){
            cur = cur.next;
        }
        TreeLinkNode root = cur;
        // 中序遍历
        ArrayList<TreeLinkNode> list1 = postOrder(root);
        for(int i = 0; i < list1.size()-1; i++){
            if(list1.get(i) == pNode){
                if(list1.get(i+1) != null){
                    return list1.get(i+1);
                }else{
                    return null;
                }
            }
        }
        return null;
    }
    public ArrayList<TreeLinkNode> postOrder(TreeLinkNode root){
        ArrayList<TreeLinkNode> list = new ArrayList();
        if(root == null){
            return list;
        }else{
            if(root.left != null){
                list.addAll(postOrder(root.left));
            }
            list.add(root);
            if(root.right != null){
                list.addAll(postOrder(root.right));
            }
            return list;
        }
    }


    /**
     * 之字形打印二叉树
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> listAll = new ArrayList();
        Stack<TreeNode> stack1 = new Stack();
        Stack<TreeNode> stack2 = new Stack();
        if(pRoot == null){
            return listAll;
        }
        stack1.push(pRoot);
        int level = 1;
        while(!stack1.empty() || !stack2.empty()){
            ArrayList<Integer> list = new ArrayList();
            if(level % 2 == 1){
                while(!stack1.empty()){
                    TreeNode top = stack1.pop();
                    list.add(top.val);
                    if(top.left != null){
                        stack2.push(top.left);
                    }
                    if(top.right != null){
                        stack2.push(top.right);
                    }
                }
                ++level;
            }else{
                while(!stack2.empty()){
                    TreeNode top = stack2.pop();
                    list.add(top.val);
                    if(top.right != null){
                        stack1.push(top.right);
                    }
                    if(top.left != null){
                        stack1.push(top.left);
                    }
                }
                ++level;
            }
            listAll.add(list);
        }
        return listAll;
    }



}
