package tx1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution12_2 {
    /**
     * 17, 二叉树的镜像
     */
    public void Mirror(TreeNode root) {
        if(root == null){
            return;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        if(root.left != null){
            Mirror(root.left);
        }
        if(root.right != null){
            Mirror(root.right);
        }
    }

    /**
     * 18, 数组中只出现一次的数字
     */
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
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

    //num1,num2分别为长度为1的数组。传出参数
    //将num1[0],num2[0]设置为返回结果
    public void FindNumsAppearOnce1(int [] array,int num1[] , int num2[]) {
        //1, 将整个数组中的数字进行异或处理，得到的即是这两个数字异或的结果
        int tmp = 0;
        for(int i = 0; i < array.length; i++){
            tmp ^= array[i];
        }
        // 2,找出异或后的二进制数中，1第一次出现的位置
        int index = 0;
        while((tmp & 1) == 0){
            index++;
            tmp = tmp >> 1;
        }
        // 3, 根据第index位是0还是1，将原数组中的数字分为两组，分别异或num1[0]、num2[0]
        for(int i = 0; i < array.length; i++){
            if(isBit1(array[i],index) == true){
                num1[0] ^= array[i];
            }else{
                num2[0] ^= array[i];
            }
        }
    }
    public boolean isBit1(int num, int index){
        if(((num >> index) & 1) == 1){
            return true;
        }
        return false;
    }

    /**
     * 20, 平衡二叉树
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null){
            return true;
        }
        int left = getLength(root.left);
        int right = getLength(root.right);
        int diff = left - right;
        if(Math.abs(diff) > 1){
            return false;
        }
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }
    // 树的高度
    public int getLength(TreeNode root){
        if(root == null){
            return 0;
        }
        int getL = getLength(root.left);
        int getR = getLength(root.right);
        return Math.max(getL,getR) + 1;
    }

    // 数字在数组中出现的次数
    public int GetNumberOfK(int [] array , int k) {
        Map<Integer,Integer> map = new HashMap();
        for(int i = 0; i < array.length; i++){
            if(map.containsKey(array[i])){
                map.put(array[i],map.get(array[i])+1);
            }else{
                map.put(array[i],1);
            }
        }
        if(map.containsKey(k)){
            return map.get(k);
        }
        return 0;
    }
}
