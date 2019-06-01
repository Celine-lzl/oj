package com.lzl;
import java.util.*;
public class Main {
    /*
    * d12  生成格雷码，参考百度格雷码定义
    * n+1位的格雷码由两部分组成，前半部分是n位格雷码
    * 后半部分是n位格雷码的逆序书写
    * */
    public String[] getGray(int n) {
        String[] Strs = null;
        if(n == 1){
            Strs = new String[]{"0","1"};
        }else{
            String[] newstrs = getGray(n-1);
            Strs = new String[2*newstrs.length];
            for(int i=0; i<newstrs.length; i++){
                Strs[i] = "0"+newstrs[i];
                Strs[Strs.length-1-i] = "1"+newstrs[i];
            }
        }
        return Strs;
    }

    /*
    * d12  不使用临时变量，交换两个数的值
    * 方法一：a = a + b; b = a - b; a = a - b;
    * 方法二：a = a ^ b; b = a ^ b; a = a ^ b;
    * */
    public int[] changeNum(int[] AB){
        AB[0] = AB[0] ^ AB[1];
        AB[1] = AB[0] ^ AB[1];
        AB[0] = AB[0] ^ AB[1];
        return AB;
    }

    /*
    * d11 个位数统计，统计每位数出现的次数
    * 将输入定义为字符串形式，用String的charAt方法，逐位取出
    * 减掉字符串0就是该位上的数字，这个数字是几就把它存到下标为几
    * 的地方，最后输出即可
    * */
    public void numTimes(){
        Scanner scanner = new Scanner(System.in);
        String str = null;
        int[] data = new int[10];
        while(scanner.hasNext()){
            str = scanner.nextLine();
            for(int i = 0; i < str.length(); i++){
                data[str.charAt(i) - '0']++;
            }
            for(int j = 0; j < data.length; j++){
                if(data[j] != 0){
                    System.out.println(j + ":" + data[j]);
                }
            }
        }
    }

    /*
    * d12 构建乘积数组
    *
    * */

}