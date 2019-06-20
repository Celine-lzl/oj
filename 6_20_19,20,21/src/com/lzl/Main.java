package com.lzl;

import java.util.*;

class Student
{
    public String name;
    public int score;
    public Student(String name,int score)
    {
        this.name=name;
        this.score=score;
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
     * d21_2 最难的问题
     */
    public static void main4(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String str=sc.nextLine();
            char[] chars=str.toCharArray();
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<chars.length;i++){
                char c=chars[i];
                if(chars[i]!=' '){
                    if(chars[i]>'E'){
                        sb.append((char)((int)c-5));
                    }else{
                        sb.append((char)((int)c+21));
                    }
                }else{
                    sb.append(c);
                }

            }
            System.out.println(sb.toString());
        }
    }

    /**
     *  d21_1 统计每个月的兔子数
     */
    public static void main3(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int monthCount = in.nextInt();
            System.out.println(getTotalCount(monthCount));
        }
        in.close();
    }

    public static int getTotalCount(int monthCount){
        if(monthCount==1 || monthCount==2){
            return 1;
        }

        return getTotalCount(monthCount-1)+getTotalCount(monthCount-2);
    }

    /**
     * d20_2 链表分割
     * 创建两个链表，分别存放小于x的和大于x的
     * 最后把两个链表合并起来
     */
    public ListNode partition(ListNode pHead, int x) {
        // write code here
        ListNode small = null;
        ListNode big = null;
        ListNode smallLast = null;
        ListNode bigLast = null;
        ListNode cur = pHead;
        while(cur != null){
            ListNode next = cur.next;
            // 小于x的放在small中
            if(cur.val < x){
                cur.next = null;
                // 小于x，尾插到small
                // 大于x，尾插到big
                if(small == null){
                    small = cur;
                }else{
                    smallLast.next = cur;
                }
                smallLast = cur;
            }
            // 大于x的放在big中
            else{
                cur.next = null;
                if(big == null){
                    big = cur;
                }else{
                    bigLast.next = cur;
                }
                bigLast = cur;
            }
            cur = next;
        }
        if(small == null){
            return big;
        }else{
            smallLast.next = big;
            return small;
        }

    }


    /**
     * d20_1 微信红包
     * 红包排序后，若有一个数字出现了超过一半
     * 排序后这个数字肯定在数组中间，统计与这个数
     * 相同的数字数目是否大于等于n/2
     */
    public int getValue(int[] gifts, int n) {
        // write code here
        Arrays.sort(gifts);
        int mid = gifts[n/2];
        int num = 0;
        for(int i = 0; i < n; i++){
            if(gifts[i] == mid){
                num++;
            }
        }
        return num <= n/2? 0:mid;
    }

    /**
     * d19_2 成绩排序
     */
        public static void main(String[] args)
        {
            Scanner scanner=new Scanner(System.in);
            while(scanner.hasNext())
            {
                int numPeople=scanner.nextInt();
                int option=scanner.nextInt();

                List<Student> stuList=new ArrayList<Student>();
                for(int i=0;i<numPeople;i++)
                {
                    stuList.add(new Student(scanner.next(), scanner.nextInt()));
                }

                //降序
                if(option==0)
                {
                    Collections.sort(stuList, new Comparator<Student>()
                    {
                        public int compare(Student o1,Student o2)
                        {
                            return o2.score-o1.score;
                        }
                    });
                }
                else if(option==1)//升序
                {
                    Collections.sort(stuList, new Comparator<Student>()
                    {
                        public int compare(Student o1,Student o2)
                        {
                            return o1.score-o2.score;
                        }
                    });
                }
                for(int i=0;i<stuList.size();i++)
                {
                    System.out.println(stuList.get(i).name+" "+stuList.get(i).score);
                }
            }
        }



    /**
     *  d19_1 子串判断
     *  直接利用String的contains方法
     */
    public boolean[] chkSubStr(String[] p, int n, String s) {
        // write code here
        boolean[] bool = new boolean[n];
        for(int i = 0; i < p.length; i++){
            if(s.contains(p[i])){
                bool[i] = true;
            }else{
                bool[i] = false;
            }
        }
        return bool;
    }
}
