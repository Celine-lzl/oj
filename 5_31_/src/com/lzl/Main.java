package com.lzl;
import java.util.*;

public class Main{
    // d10 机器人走网格
    public static int walkRobert(int x, int y){
        if(x == 0 || y == 0){
            return 0;
        }
        // 即只剩一条直线，故只有一种走法
        if(x == 1 || y ==1){
            return 1;
        }
        // x,y不是坐标，是网格的数目
        return walkRobert(x-1,y) + walkRobert(x,y-1);
    }

    //d10 Fibonacci数列
        public static void littleFib(){
            Scanner scanner = new Scanner(System.in);
            int n;
            while (scanner.hasNext()) {
                n = scanner.nextInt();
                int a = 0;
                int b = 1;
                // 采用迭代法而不是递归，因为递归会多次计算重复的数字，效率并不高
                while(b < n){
                    int b_temp = a + b;
                    a = b;
                    b = b_temp;
                }
                // 此时b-n 不一定是最小的,有可能n-a更小
                System.out.println((n - a) > (b - n)? (b-n):(n-a));
            }
        }


    //d9 求n的阶乘中末尾有几个0
    /*
     * 0的出现自然是2*5相乘得到的，但由于2出现的次数太多了，
     * 本题就转换成求5的倍数的个数，有一个5，必定至少有一个2，
     * 故一定会有5的倍数个0
     * */
    public static void zeroNum(){
        Scanner scanner = new Scanner(System.in);
         int n;
         while(scanner.hasNext()){
            int res = 0;
            n = scanner.nextInt();
            for(int i = n; i >= 5; i--){
              int temp = i;
              // 加上while循环可以正确记录一些特殊数字，比如25,125,625等5的次方数
              while(temp % 5 == 0){
                  res++;
                  temp = temp / 5;
              }
          }
          System.out.println(res);
      }
  }

 // d9 回文解码
    public static void reverseOpen(){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            StringBuffer sb = new StringBuffer(s);
            int n = in.nextInt();
            for(int i = 0; i < n; i++){
                int start = in.nextInt();
                int end = start + in.nextInt();
                StringBuffer ssb = new StringBuffer(sb.substring(start,end));
                sb.insert(end,ssb.reverse()); // 用append方法会编译不过
            }
            System.out.println(sb);
        }
    }

   // d8 删除
    public static void deleteNume(){
        Scanner scanner = new Scanner(System.in);
        int n;
        while(scanner.hasNext()){
            n = scanner.nextInt();
            if(n > 1000){
                n = 999;
            }
            List<Integer> list = new ArrayList<Integer>();
            for(int i = 0; i < n; i++){
                list.add(i);
            }
            int a = 0;
            while(list.size() > 1){
                a = (a + 2) % list.size(); // 参考循环队列的移除公式
                list.remove(a);
            }
            System.out.println(list.get(0));
        }
    }

  // day8 n-1个数里的最小的k个
    public static void min_K(){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
                String str = sc.nextLine();
                String[] s = str.split(" ");
                // s数组中的最后一个数是k，不算在num数组中
                int[] num = new int[s.length-1];
                for(int i = 0; i < s.length-1; i++){
                    num[i] = Integer.parseInt(s[i]);
                }

                int k = Integer.parseInt(s[s.length-1]);
                int start = 0;
                int end = num.length-1;
                // 基准值在的位置
                int index = qSort(num, start,end);
                while(index != k){
                    if(index>k){
                        end = index - 1;
                        index = qSort(num, start, end);

                    }else{
                        start = index + 1;
                        index = qSort(num, start, end);
                    }
                }
                Arrays.sort(num, 0, k);
                for(int i=0;i<k;i++){
                    System.out.print(i==k-1?num[i]:num[i] + " ");
                }
            }
        }
        public static int qSort(int[] num, int start, int end){
            int dig = num[end];
            int right = end;
            while(start<end){
                while(start < end && num[start] <= dig) {
                    start++;
                }
                while(start < end && num[end] >= dig)
                {
                    end--;
                }
                swap(num,start,end);
            }
            swap(num,start,right);
            return start;
        }
        public static void swap(int[] num,int x,int y){
            int temp = num[x];
            num[x] = num[y];
            num[y] = temp;
        }


    }






