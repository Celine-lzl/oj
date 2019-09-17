import java.util.ArrayList;
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
public class Main_9_16 {

    /**
     * 之字形打印二叉树
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if(pRoot == null){
            return null;
        }
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        return getPrint(pRoot,list);
    }
    public ArrayList<ArrayList<Integer>> getPrint(TreeNode root, ArrayList<ArrayList<Integer>> list){
        if(root == null){
            return null;
        }
        ArrayList<Integer> list1 = new ArrayList();
        list1.add(root.val);
        list.add(list1);
        if(root.right != null){
            getPrint(root.right,list);
        }
        if(root.left != null){
            getPrint(root.left,list);
        }
        return list;
    }

    /**
     * 构建乘积数组
     */
    public int[] multiply(int[] A) {
        // 最直接的方法，暴力相乘法，遇到 j == i 时跳过
        // 时间复杂度为 O(n^2)
        int[] res = new int[A.length];
        for(int i = 0; i < A.length; i++){
            res[i] = 1;
            for(int j = 0; j < A.length; j++){
                if(j == i){
                    continue;
                }
                res[i] *= A[j];
            }
        }
        return res;
    }

    // 我们可以通过构建一个乘积矩阵来解决这个问题
    // 我们知道B[i]是一个n-1个数相乘的结果，不乘A[i]这个数
    // 因此我们可以认为B[i]是n个数相乘的结果，我们把A[i]全部赋值为1
    // 因此这就是一个矩阵，B[i] = A[0]*A[1]*A[2]...*A[i-1]
    // 加上 A[i+1]*A[i+2]*A[i+3]*...*A[n-2]*A[n-1]
    // 这就像一个矩阵，矩阵的对角线值为1
    // 每一个B[i]就是矩阵中的第i行的全部数相乘的结果
    // 按照对角线，我们可以把矩阵分为上三角和下三角两部分
    public int[] multiply1(int[] A) {
        int[] B = new int[A.length];
        B[0] = 1;
        // 矩阵下三角的值（左下角）
        for(int i = 1; i < A.length; i++){
            B[i] = B[i-1] * A[i-1];
        }
        // 矩阵上三角（右上角）
        int temp = 1;
        for(int j = A.length-2; j >= 0; j--){
            temp *= A[j+1]; // 当前下三角的值
            B[j] *= temp;   // 与上三角结合的值
        }
        return B;
    }

    // 数组中重复的数字
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        int[] data = new int[length];
        for(int i = 0; i < length; i++){
            //data中不包含这个数，就把它放进data
            if(data[numbers[i]] == 0){
                data[numbers[i]] = 1;
            }
            //data中包含这个数，就把它赋给duplication
            else{
                duplication[0] = numbers[i];
                return true;
            }
        }
        return false;
    }
}
