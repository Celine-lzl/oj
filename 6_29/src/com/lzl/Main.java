package com.lzl;
import java.util.*;
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
public class Main{

    /**
     *  d16_1 洗牌
     */
        public static void main7(String[] args) {
            Scanner sc = new Scanner(System.in);
            int groups = sc.nextInt();
            while (groups-- > 0){
                int n = sc.nextInt();
                int k = sc.nextInt();
                int[] res = new int[2*n];
                for(int i=0;i<2*n;i++){
                    int tmp = i + 1;
                    for(int j = 0; j < k;j++){
                        if (tmp <= n) tmp = 2*tmp - 1;
                        else tmp = 2 * (tmp - n);
                    }
                    res[tmp - 1]=sc.nextInt();
                }
                //输出
                if(res.length> 0) System.out.print(res[0]);
                for(int i = 1;i< 2*n;i++){
                    System.out.print(" "+res[i]);
                }
                System.out.println();
            }
        }

    /**
     *  d16_2 统计同成绩人生人数
     */
     public static void main2(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int count = 0;
            int n = scanner.nextInt();
            int[] data = new int[n];
            for(int i = 0; i < n; i++){
                data[i] = scanner.nextInt();
            }
            int stand = scanner.nextInt();
            for(int i = 0; i < n; i++){
                if(data[i] == stand){
                    count++;
                }
            }
            System.out.println(count);
        }
     }

    /**
     *  d17_2 二叉树镜像
     */
    public void Mirror(TreeNode root) {
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if(root.left != null){
            Mirror(root.left);
        }
        if(root.right != null){
            Mirror(root.right);
        }
    }

    /**
     * d18_1 DNA序列
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan=new Scanner(System.in);
        String str=scan.nextLine();
        int len=str.length();

        for(int i=1;i<=len;i++) {
            TreeSet <String> set=new TreeSet <String> ();
            for(int j=0;j<len-i;j++) {
                set.add(str.substring(j, j+i));
            }
            if(set.size()<Math.pow(4, i)) {
                System.out.print(i);
                break;
            }
        }
    }

    /**
     * d18_2 百万富翁问题
     */
    public static void main1(String[] args){
        long Rich = 0;
        long Strange = 0;
        for(int i = 1; i <= 30; i++){
            Rich += 10;
            Strange = Strange + (long)Math.pow(2,i-1);
        }
        System.out.println(Rich+" "+Strange);
    }
    //-----------------------------------------------------------------------------------
    public static void selectSort(int[] a) {
        if((a == null) || (a.length == 0))
            return ;
        for(int i = 0;i < a.length - 1;i ++){
            int minIndex = i; // 无序区的最小数据数组下标
            for(int j = i + 1;j < a.length;j ++){
                // 在无序区中找到最小数据并保存其数组下标
                if(a[j] < a[minIndex]){
                    minIndex = j;
                }
            }
            // 将最小元素放到本次循环的前端
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
    }
    public static void main6(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            int countA = 0;
            int countG = 0;
            int countC = 0;
            int countT = 0;
            Stack<Character> stack = new Stack();
            for(int i = 0; i < s.length(); i++){
                stack.push(s.charAt(i));
            }
            while(!stack.empty()){
                char g = stack.peek();
                if(g == 'A'){
                    countA++;
                    stack.pop();
                }
                else if(g == 'G'){
                    countG++;
                    stack.pop();
                }
                else if(g == 'C'){
                    countC++;
                    stack.pop();
                }
                else{
                    countT++;
                    stack.pop();
                }
            }
            int[] res = {countA,countG,countC,countT};
            selectSort(res);

            System.out.println(res[0]+1);
        }
    }
}