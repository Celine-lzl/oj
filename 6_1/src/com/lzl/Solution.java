package com.lzl;

public class Solution {


    // jz2 替换空格
    public String replaceSpace(StringBuffer str) {
        if(str == null){
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < str.length(); i++){
            if(String.valueOf(str.charAt(i)).equals(" ")){
                sb.append("%20");
            } else{
                sb.append(str.charAt(i));
            }
        }
        return String.valueOf(sb);
    }

    //jz1 二维数组中的查找
    public boolean Find(int target, int [][] array) {
        // 选基准值，要选右上角或者左下角的
        // 比如右上角是一行中的最大，一列中的最小
        // 它比目标小，可以剔除这一行，比目标大，剔除这一列
        // 左上角和右下角都是最小或最大，不能剔除谁
        int row = 0;
        int col = array[0].length - 1;
        while(row <= array.length - 1  && col >= 0){
            int stand = array[row][col];
            if(target == stand){
                return true;
            }
            if(stand > target){
                // 剔除stand这一列
                col--;
            }
            if(stand < target){
                // 剔除stand这一行
                row++;
            }
        }
        return false;
    }
}
