package day27;
import java.util.*;
import java.math.BigInteger;

public class Main{
    /**
     * d26_1 三角形
     */
    public void isTrangle(){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            BigInteger a = sc.nextBigInteger();
            BigInteger b = sc.nextBigInteger();
            BigInteger c = sc.nextBigInteger();
            if(a.add(b).compareTo(c) == 1 && a.add(c).compareTo(b) == 1
                    && c.add(b).compareTo(a) == 1){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
        }
    }

    /**
     * d26_2 不用加减乘除做加法
     */
    public int Add(int num1,int num2) {
        while(num2 != 0){
            int sum = num1 ^ num2;
            int carry = (num1 & num2) << 1 ;
            num1 = sum;
            num2 = carry;
        }
        return num1;
    }


    /**
     * jd23_2 数字分类
     * 第一题是平衡二叉树检验
     */
    public static void main(String []args){
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
     * dj24_2 最高分是多少（1是小易的升级之路）
     */
    public static void main1(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();  //学生人数
            int times = sc.nextInt();  // 询问次数
            int[] score = new int[n]; // 初始成绩
            for(int i = 0; i < n; i++){
                score[i] = sc.nextInt();
            }
            for(int i = 0; i < times; i++){
                String ss = sc.next();
                int a = sc.nextInt();
                int b = sc.nextInt();
                if("Q".equals(ss)){
                    int s = Math.min(a,b);
                    int d = Math.max(a,b);
                    int max = score[s-1];
                    for(int j = s-1; j < d; j++){
                        if(score[j] > max){
                            max = score[j];
                        }
                    }
                    System.out.println(max);
                }
                if("U".equals(ss)){
                    score[a-1] = b;
                }
            }
        }
    }
}