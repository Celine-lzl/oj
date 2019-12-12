package day29_30;

import java.util.Scanner;

public class Main {
    /**
     * d30_1 最难的问题
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.next();
            char[] data = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < data.length; i++){
                if(data[i] >= 'A' && data[i] <= 'U'){
                    char tmp = (char)(data[i]+5);
                    sb.append(tmp);
                }else if(data[i] >= 'V' && data[i] <= 'Z'){
                    int diff = data[i] - 'V';
                    System.out.println(data[i] + " 、" + diff);
                    char tmp = (char)('A'+diff);
                    sb.append(tmp);
                }else{
                    sb.append(data[i]);
                }
            }
            System.out.println(sb);
        }
    }

    /**
     * d29_2 正整数数组的最小不可组成和
     */
    public int getFirstUnFormedNum(int[] arr) {
        int min = arr[0];
        int max = 0;
        int len = arr.length;
        // 确定 [最小值，最大值]
        for(int i = 0; i < arr.length; i++){
            min = min < arr[i] ? min : arr[i];
            max += arr[i];
        }
        // 结果数组，比原数组长度多1，是因为要表示放入第i个数（第i个数下标是i-1）
        // dp[j]表示背包承重为i时，能放入的价值最高的东西
        int[] dp = new int[max+1];
        for (int i = 0; i < len; ++i) {
            // 有length个数据，有length个阶段
            // {2, 3, 5}
            //i=0--d[10]=2 d[9]=2 d[8]=2 d[7]=2...d[2]=2
            //i=1--d[10]=5 d[9]=5...d[5]=5 d[4]=3 d[3]=3
            //i=2--d[10]=10 d[9]=8 d[8]=8 d[7]=7 d[6]=5 d[5]=5
            for (int j = max; j >= arr[i]; --j) {
                //逆序判断背包承重中能够放入的数据
                //当数组中只有2的时候，背包承重从2-10都可以放入2的数值
                //当数组中放入2和3的时候，背包承重从5-10可以放入5，3-4放入3，2只能放入2
                //当数组中放入2，3，5时，背包承重10放入10，8-9放入8，7放入7，5-6放入5...
                //dp[j-arr[i]]意思是背包承重为j时，如果已经放置了arr[i]的重量后还能放置的最大重量
                if (dp[j] < dp[j - arr[i]] + arr[i]) // 对每个承重计算当前最大能放置重量
                    dp[j] = dp[j - arr[i]] + arr[i]; // 更新背包中能够放入的最大值
                else
                    dp[j] = dp[j];
            }
        }
        // 最后当承重为n时，放入的重量不为n则认为是最大不可求和
        for (int i = min; i <= max; ++i) {
            if (i != dp[i])
                return i;
        }
        return max + 1;
    }


    /**
     * d29_1 有假币
     * 分析发现，每次把硬币分为三堆是最快的找法
     * 如果硬币n是3的倍数，只需要把n/3继续递归即可
     * 如果n不是3的倍数，有两种分法：
     *  1：(n+2)/3   (n+2)/3  n-2*((n+2)/3)
     *  2: n/3+1    (n-(n/3+1))/2  (n-(n/3+1))/2
     *  这两种分法都是把三堆中最多的那堆拿出来再递归，没发现有啥区别
     *  第一种分法有人做了判断，不确定第一堆和第二堆谁多，我觉得第一堆肯定大于等于第二堆呀
     */
    public static void main1(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int times = findFake(n);
            System.out.println(times);
        }
    }
    public static int findFake(int n){
        if(n <= 1){
            return 0;
        }
        if(n == 2 || n == 3){
            return 1;
        }
        if(n % 3 == 0){
            return 1 + findFake(n/3);
        }
        return 1 + findFake((n/3)+1);
    }
}
