package com.Main_7_23;

import java.util.Scanner;
import java.util.*;

public class Main {
    /**
     * d45_1 乒乓球筐
     * 利用map的特点
     */
    public static void main8(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String boxA = sc.next();
            String boxB = sc.next();
            HashMap<String, Integer> hashMapBoxA = new HashMap<String, Integer>();
            HashMap<String, Integer> hashMapBoxB = new HashMap<String, Integer>();
            for (int i = 0; i < boxA.length(); i++) {
                if (hashMapBoxA.containsKey(boxA.charAt(i) + "")) {
                    hashMapBoxA.put(boxA.charAt(i) + "", hashMapBoxA.get(boxA.charAt(i) + "") + 1);
                } else {
                    hashMapBoxA.put(boxA.charAt(i) + "", 1);
                }
            }

            for (int i = 0; i < boxB.length(); i++) {
                if (hashMapBoxB.containsKey(boxB.charAt(i) + "")) {
                    hashMapBoxB.put(boxB.charAt(i) + "", hashMapBoxB.get(boxB.charAt(i) + "") + 1);
                } else {
                    hashMapBoxB.put(boxB.charAt(i) + "", 1);
                }
            }

            int flag = 0;
            for (String key : hashMapBoxB.keySet()) {
                if (!hashMapBoxA.containsKey(key)) {
                    flag = 1;
                    break;
                } else {
                    if (hashMapBoxA.get(key) < hashMapBoxB.get(key)) {
                        flag = 1;
                        break;
                    }
                }
            }
            if (flag == 1) {
                System.out.println("No");
            } else {
                System.out.println("Yes");
            }
        }
    }

    /**
     * d46_1 数组中出现次数超过一半的数字
     * 排序后，中间的数字就应该是出现次数超过一半的数字
     * Arrays.sort()背后仍是快排，复杂度略高
     * 可以采用士兵守擂的方法，开始士兵数count=1
     * 假设第一个数是一个士兵，第二个数若和他不同，则认为是敌人士兵
     * 将count--，count为0时将守擂的人换成敌人，若相同，则count++
     */
        public int MoreThanHalfNum_Solution(int [] array) {
            Arrays.sort(array);
            int len = array.length;
            int mid = array[len/2];
            int count = 0;
            for(int i = 0; i < array.length; i++){
                if(array[i] == mid){
                    count++;
                }
            }
            if(count > len/2){
                return mid;
            }else{
                return 0;
            }
        }

    /**
     * d46_2 简单错误记录
     * 首先对接受到的字符串进行分割，分割出文件名，行号
     * 对大于16位的文件名进行处理，将文件名和行号整体作为key，存入map
     * 对于要输出最后8个文件，如果大于8个，应该从map中第size-8个位置开始输出
     * 取一个计数器，遍历一个文件名，计数器加1，当计数器的值大于size-8时输出
     */
    public static void main3(String[] args){
        Scanner sc = new Scanner(System.in);
        // LinkedHashMap能保证不被排序
        Map<String,Integer> map = new LinkedHashMap<String,Integer>();
        while(sc.hasNext()){
            String s = sc.nextLine();
            match(s,map);
        }
        // 只保留最后8个文件
        // 当前正遍历到的文件的位置
        int count = 0;
        // 如果文件总数超过8个，应该从map.size-8个位置开始输出
        for(String keyy : map.keySet()){
            count++;
            if(count > (map.size()-8)){
                System.out.println(keyy+" " + map.get(keyy));
            }
        }
    }
    public static void match(String s,Map<String,Integer> map){
        // \\s+用来匹配任意空白字符
        String[] line = s.split("\\s+");
        // 分割\
        String[] file = line[0].split("\\\\");
        // 取得行号
        int lineNum = Integer.parseInt(line[1]);
        // 取得文件名
        String fileName = file[file.length-1];
        // 处理文件名大于16的情况
        if(fileName.length() > 16){
            fileName = fileName.substring(fileName.length()-16);
        }
        // 将文件名和行号作为一个整体存入map的key值
        String key = fileName+" "+lineNum;
        if(map.containsKey(key)){
            map.put(key, map.get(key) + 1);
        }else{
            map.put(key,1);
        }
    }

    /**
     *  d39_1 二维数组打印
     *  打印4*4的数组，顺序为 (0,3)  -->  (0,2)(1,3)
     *  (0,1)(1,2)(2,3) --> (0,0)(1,1)(2,2)(3,3)
     *  (1,0)(2,1)(3,2) --> (2,0)(3,1) --> (3,0)
     *  我们把每条对角线的打印看做一次打印过程
     *  每次打印过程我们可以发现 x,y 坐标都是在加1的
     *  上三角，每次的第一个坐标startY依此递减
     *  下三角，每次的第一个坐标startX依此递增
     *  当startX增加到大于等于n时，遍历就结束了
     */

    public int[] arrayPrint(int[][] arr, int n) {
        // write code here
        // 结果数组
        int[] res = new int[n*n];
        // 结果数组中的下标索引
        int index = 0;
        // 初始地点的横纵坐标
        int startX = 0;
        int startY = n-1;
        // 终止条件
        while(startX < n){
            //正在遍历的元素的下标(now_x,now_y)
            int now_x = startX;
            int now_y = startY;
            // 在数组下标范围内
            while(now_x < n && now_y < n){
                res[index] = arr[now_x][now_y];
                index++;
                now_x++;
                now_y++;
            }
            // 切换另一条对角线打印
            if(startY > 0){
                startY--;
            }else{
                startX++;
            }
        }
        return res;
    }

    /**
     * d39_2 回文串
     * 如果一个字符串增加一个字符可以变成回文字符串
     * 那么原字符串应该本身就是个回文字符串或者原
     * 字符串中只有一个字符是落单的，其他的都已经回文了
     * 如果该字符串第一个和最后一个字符已经不相等了
     * 那么从第一个到倒数第一个或者从第二个到最后一个应该有一个是回文的
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            int start = 0;
            int end = s.length()-1;
            boolean flag = true;
            while(start < end){
                if(!(s.substring(0,1).equals(s.substring(s.length()-1)))){
                    flag = (isHuiWen(s.substring(start,end))) ||
                            isHuiWen(s.substring(start+1));
                    break;
                }
                start++;
                end--;
            }
            System.out.println(flag? "YES" : "NO");
        }
    }
    public static boolean isHuiWen(String s){
        StringBuffer sb = new StringBuffer(s);
        if(sb.reverse().toString().equals(s)){
            return true;
        }else{
            return false;
        }
    }
}



