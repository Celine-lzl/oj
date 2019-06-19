package com.lzl;
import java.util.LinkedList;
import java.util.Scanner;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class Main {
    /**
     * d22_1 到底买不买
     * 将摊主有的珠子保存在LinkedList内，遍历，若小红也有
     * 将这颗珠子删除，计数+1，若最后计数和小红珠子数相同
     * 则说明应该买
     */
    public static void main4(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String boss = sc.next(); // 摊主的珠子
            String red = sc.next();  // 小红的珠子
            LinkedList<Character> list = new LinkedList();
            // 摊主珠子数组
            for(int i = 0; i < boss.length(); i++){
                list.add(boss.charAt(i));
            }
            // 小红和摊主有相同珠子的个数
            int count = 0;
            // 遍历计数相同珠子的个数
            for(int j = 0; j < red.length(); j++){
                char ch = red.charAt(j);
                for(int k = 0; k < list.size(); k++){
                    if(ch == list.get(k)){
                        list.remove(k);
                        count++;
                        break;
                    }
                }
            }
            if(count == red.length()){
                System.out.println("Yes "+ (boss.length()-red.length()));
            }else{
                System.out.println("No "+ (red.length()-count));
            }
        }
        sc.close();
    }

    /**
     *  d22_2 链式A+B
     *  创建一个结果链表，保存a,b求和的结果
     *  建立进位标识位，考虑到最高位有可能还会向前进位的情况
     */
    public ListNode plusAB(ListNode a, ListNode b) {
        // write code here
        ListNode dummyHead = new ListNode(0); // 结果链表
        ListNode p = a, q = b, curr = dummyHead; // curr是链表当前遍历的位置
        int carry = 0; // 进位标志位
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0; // 若两个链表不一样长
            int y = (q != null) ? q.val : 0; // 在较短的链表前面补0
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        // 当99+1这种特殊情况时，最高位还会向前进位时
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

   /*
   * d23_2 数字分类
   * 按照题意写下来即可，A4需要注意，要求平均值精确到小数点后一位
   * 需要将小数点前后分别运算，小数点后的计算要考虑其下一位是否有四舍五入
    */
   public static void main3(String []args){
       Scanner in=new Scanner(System.in);
       int N=in.nextInt();
       int num[]=new int[N];
       int A1=0,A2=0,A3=0,A4=0,A5=0,flag=1,count=0;
       for(int i=0;i<N;i++){
           num[i]=in.nextInt();
           if(num[i]%5==0){
               if(num[i]%2==0)
                   A1+=num[i];
           }
           if(num[i]%5==1){
               A2+=flag*num[i];
               flag=-flag;
           }
           if(num[i]%5==2){
               A3++;
           }
           if(num[i]%5==3){
               A4+=num[i];
               count++;
           }
           if(num[i]%5==4){
               if(num[i]>A5)
                   A5=num[i];
           }
       }
       if(A1!=0){
           System.out.print(A1+" ");
       }else{
           System.out.print('N'+" ");
       }

       if(A2!=0){
           System.out.print(A2+" ");
       }else{
           System.out.print('N'+" ");
       }

       if(A3!=0){
           System.out.print(A3+" ");
       }else{
           System.out.print('N'+" ");
       }

       if(A4!=0){
           System.out.print(A4/count+"."+(int)((((A4*100/count)%100)+5)/10)+" ");
       }else{
           System.out.print("N"+" ");
       }

       if(A5!=0){
           System.out.print(A5);
       }else{
           System.out.print("N");
       }
   }

    /**
     * d23_1 二叉树平衡检查
     * 每个节点的左右子数的高度差都不超过1
     */
    public boolean isBalance(TreeNode root) {
        // write code here
        if(root == null){
            return true;
        }
        if(root.left == null && root.right == null){
            return true;
        }
        if(Math.abs(getHeight(root.left)-getHeight(root.right)) > 1){
            return false;
        }else{
            return isBalance(root.left) && isBalance(root.right);
        }
    }
    // 求二叉树高度
    public static int getHeight(TreeNode r){
        if(r == null){
            return 0;
        }
        int left = getHeight(r.left);
        int right = getHeight(r.right);
        return Math.max(left,right)+1;
    }

    /**
     * d24_1 小易的升级之路
     */
    public static void main1(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = 0; // n个怪兽
        int a = 0; // 初始能量
        while(scanner.hasNext()){
            n = scanner.nextInt();
            a = scanner.nextInt();
            int[] bi = new int[n]; // 怪兽能量数组
            for(int i = 0; i < n; i++){
                bi[i] = scanner.nextInt();
            }
            // 开始打怪
            for(int i = 0; i < n; i++){
                if(bi[i] > a){
                    a += Gcd(a,bi[i]);
                }else{
                    a += bi[i];
                }
            }
            System.out.println(a);
        }
    }
    // 求最大公约数
    public static int Gcd(int n, int m){
        while(n % m != 0){
            int c = n % m;
            n = m;
            m = c;
        }
        return m;
    }

    /**
     * d24_2 最高分是多少
     * 在'Q'和'C'时，给出的a,b并没有说a一定比b大
     * 不能直接在[a,b]内计算有可能是[b,a]
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int m = 0; //m次操作
        int n = 0; //n个学生
        while(scanner.hasNext()){
            n = scanner.nextInt();
            m = scanner.nextInt();
            int[] data = new int[n]; //保存学生成绩
            for(int i = 0; i < n; i++){
                data[i] = scanner.nextInt();
            }
            for(int j = 0; j < m; j++){ // m次操作
                String c = scanner.next();
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                if(c.equals("Q")){ // Q：找出A,B中的最大值
                    int start = Math.min(a,b); // 开始下标
                    int end = Math.max(a,b);   // 结束下标
                    int max = data[start-1];
                    for(int k = start; k < end; k++){
                        max = Math.max(max,data[k]);
                    }
                    System.out.println(max);
                }
                if(c.equals("U")){ // U：更新学生A的成绩为B
                    data[a-1] = b;
                }
            }
        }
    }
}
