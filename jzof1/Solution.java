package jzof1;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        int[][] data = {{1},{2},{3},{4},{5}};
        ArrayList<Integer> list = new ArrayList<>();
        list = printMatrix(data);
        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i)+"、");
        }
    }

    /**
     * 顺时针打印矩阵
     */
    public static ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList();
        if(matrix.length == 0 || matrix[0].length == 0){
            return list;
        }
        int startX = 0;
        int startY = 0;
        int endX = matrix.length - 1;
        int endY = matrix[0].length - 1;
        while(startX <= endX && startY <= endY){
            // 当只剩下一行时
            if(startX == endX){
                for(int i = startY; i <= endY; i++){
                    list.add(matrix[startX][i]);
                }
                return list;
            }
            // 当只剩下一列时
            if(startY == endY){
                for(int i = startX; i <= endX; i++){
                    list.add(matrix[i][startY]);
                }
                return list;
            }
            // 有多行多列时
            // 1，第一行
            for(int i = startY; i <= endY; i++){
                list.add(matrix[startX][i]);
            }
            // 2，最后一列
            for(int i = startX+1; i <= endX; i++){
                list.add(matrix[i][endY]);
            }
            // 3，最后一行
            if(startY != endY) {
                for (int i = endY - 1; i >= startY; i--) {
                    list.add(matrix[endX][i]);
                }
            }
            // 4，第一列
            if(startX != endX) {
                for (int i = endX - 1; i > startX; i--) {
                    list.add(matrix[i][startX]);
                }
            }
            startX += 1;
            endX -= 1;
            startY += 1;
            endY -= 1;
        }
        return list;
    }

    public ArrayList<Integer> printMatrix2(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList();
        int row = matrix.length;
        while(row != 0){
            // 1，只打印第一行
            for(int i = 0; i < matrix[0].length; i++){
                list.add(matrix[0][i]);
            }
            if(row == 1){
                break;
            }
            // 2，删除第一行，将数组逆时针旋转
            matrix = turn(matrix);
            // 3，更新新的行数
            row = matrix.length;
        }
        return list;
    }
    public int[][] turn(int[][] arr){
        int row = arr.length;
        int col = arr[0].length;
        int[][] newarr = new int[col][row-1];
        for(int j = col-1; j >= 0; j--){
            for(int i = 1; i < row; i++){
                newarr[col-1-j][i-1] = arr[i][j];
            }
        }
        return newarr;
    }

    /**
     * 调整数组顺序使奇数位于偶数前
     */
    public static void reOrderArray(int [] array) {
        boolean flag = true;
        for (int i = 0; i < array.length;i++)
        {
            // 这里j必须从0开始取值，如果j取i+1，偶数全在前面时就会出错
            for (int j = 0; j < array.length-1 ;j++)
            {
                if (array[j] % 2 == 0 && array[j + 1] % 2 == 1) //前偶后奇交换
                {
                    swap(array,j,j+1);
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
    }
    public static void swap(int[] array, int x, int y){
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

}
