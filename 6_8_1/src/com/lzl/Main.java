package com.lzl;

import java.util.Scanner;
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}
public class Main {
    // d16_1 洗牌
    public static void main3(String[] args) {
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

    // d16_2 统计同成绩学生人数
    public static void main2(String[] args){
        Scanner scanner = new Scanner(System.in);
        int N = 0;
        int target = 0;
        int count = 0;
        while(scanner.hasNext()){
            N = scanner.nextInt();
            int[] data = new int[N];
            for(int i = 0; i < N; i++){
                data[i] = scanner.nextInt();
            }
            target = scanner.nextInt();
            for(int i = 0; i < data.length; i++){
                if(data[i] == target){
                    count++;
                }
            }
            System.out.println(count);
        }
    }
    // 17_1 火车进站

    // d17_2 二叉树的镜像
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

    // d18_1 DNA序列
    public static void main1(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String s = scanner.nextLine();
            int n = scanner.nextInt();
            int max = 0;
            int index = 0;
            for(int i = 0; i <= s.length()-n; i++){
                int count = 0;
                for(int j = i; j < i+n; j++){
                    if(s.charAt(j) == 'C' || s.charAt(j) == 'G'){
                        count++;
                    }
                }
                if(max < count){
                    max = count;
                    index = i;
                }
            }
            System.out.println(s.substring(index,index+n));
        }
    }
    // d18_2 百万富翁问题
    public static void main(String[] args){
        long Rich = 0;
        long Strange = 0;
        for(int i = 1; i <= 30; i++){
            Rich += 10;
            Strange = Strange + (long)Math.pow(2,i-1);
        }
        System.out.println(Rich+" "+Strange);
    }
}
