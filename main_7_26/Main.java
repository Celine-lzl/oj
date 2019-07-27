package main_7_26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    // d48_左右最值最大差
    // 左右最值最大差
    // 从第一个元素看起，如果第二个元素大于他，那第二个元素也大不过整个数组最大值
    // 第二个元素要是小于它，那要求左边部分的最大值，仍是第一个元素
    public int findMaxGap(int[] A, int n) {
        // write code here
        int begin = A[0];
        int end = A[A.length-1];
        Arrays.sort(A);
        int max = A[A.length-1];
        return Math.max(max-begin,max-end);
    }

    /**
     * d48_2 顺时针打印矩阵
     */
        public int[] clockwisePrint(int[][] mat, int n, int m) {
            // write code here
            int startX = 0;
            int endX = m - 1;
            int startY = 0;
            int endY = n - 1;
            int index = 0;
            int[] result = new int[n * m];
            while (startX <= endX && startY <= endY) {
                // 从左到右打印
                if(startX <= endX){
                    for (int i = startX; i <= endX; i++) {
                        result[index++] = mat[startY][i];
                    }
                }
                // 从上往下打印
                if (startY < endY) {
                    for (int i = startY + 1; i <= endY; i++) {
                        result[index++] = mat[i][endX];
                    }
                }
                // 从右往左打印
                if (startX < endX && endY > startY) {
                    for (int i = endX - 1; i >= startX; i--) {
                        result[index++] = mat[endY][i];
                    }
                }
                // 从下往上打印
                if (startY < endY && endX > startX) {
                    for (int i = endY - 1; i >= startY + 1; i--) {
                        result[index++] = mat[i][startX];
                    }
                }
                startX++;
                endX--;
                startY++;
                endY--;
            }
            return result;
        }

    /**
     *  d37_2 木棒拼图
     *  能否拼成面积大于0的多边形所需要满足的条件是
     *  多边形中最长的边小于剩余所有边之和的长度
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            // n次操作
            int n = sc.nextInt();
            // 增加或删除的操作
            int[] oper = new int[n];
            // 每次操作对应的木棒的长度
            int[] len = new int[n];
            // 最终得到的木棒的长度和个数
            List<Integer> list = new ArrayList();
            for(int i = 0; i < n; i++){
                oper[i] = sc.nextInt();
                len[i] = sc.nextInt();
                if(oper[i] == 1){
                    list.add(len[i]);
                }else {
                    list.remove(new Integer(len[i]));
                }
                int[] temp = new int[list.size()];
                for(int k = 0; k < list.size(); k++){
                    temp[k] = list.get(k);
                }
                // 当前木棒中的最大值
                Arrays.sort(temp);
                int max = temp[temp.length-1];
                // 剩余木棒的长度和
                int sum = 0;
                for(int j = 0; j < list.size(); j++){
                    sum += list.get(j);
                }
                if(sum - max > max){
                    System.out.println("Yes");
                }else{
                    System.out.println("No");
                }
            }
        }
    }
}

