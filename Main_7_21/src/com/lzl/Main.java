package com.lzl;

import java.util.*;

public class Main{
    /**
     * 电话号码
     * 根据指定元素求出在字符串中的下标索引String.indexOf(String)
     * list可以查重，利用contains方法
     */
    public static void main1(String[] args){
        String symbol="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String number="222333444555666777788899991234567890";
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            int n=scanner.nextInt();
            ArrayList<String> arrayList=new ArrayList<String>();
            for(int i=0;i<n;i++){
                String str=scanner.next();
                str=str.replace("-","");
                String result="";
                for(int j=0;j<7;j++){
                    result+=number.charAt(symbol.indexOf(str.charAt(j)+""));
                }
                result=result.substring(0,3)+"-"+result.substring(3,7);
                if(!arrayList.contains(result))
                    arrayList.add(result);
            }
            Collections.sort(arrayList);
            for(int j=0;j<arrayList.size();j++){
                System.out.println(arrayList.get(j));
            }
            System.out.println();
        }
    }
    /**
     *  d41_1连续最大和_  只通过了30%
     *  错误的原因是只考虑了全是正数的情况
     *  如果全是负数，就应该选出最小的负数，即Math.max(data[i],sum+data[i])
     *  改正后的代码如下
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[] data = new int[n];
            for(int i = 0; i < n; i++){
                data[i] = sc.nextInt();
            }
            int sum = 0;
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < n; i++ ){
               sum = Math.max(data[i],sum+data[i])
                if(sum > max){
                    max = sum;
                }
            }
            System.out.println(max);
        }

    /**
     *  d41_2 坐标移动 也没通过，每日一题给的答案没有问题，说答案错了都是幻觉
     */
//    这个糟糕的错误的想法
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        String s = sc.nextLine();
//        String[] res = s.split(";");
//        int x = 0;
//        int y = 0;
//        for(int i = 0; i < res.length; i++){
//            String single = res[i];
//            String lett = single.substring(0,1);
//            String num1 = single.substring(1);
//            if(lett.equals("W")||lett.equals("S") ||
//                    lett.equals("A") || lett.equals("D") &&
//                    Integer.valueOf(num1) >= 0 && Integer.valueOf(num1) <= 99){
//                switch(lett) {
//                    case "W":
//                        y += Integer.valueOf(num1);
//                        break;
//                    case "S":
//                        y -= Integer.valueOf(num1);
//                        break;
//                    case "A":
//                        x += Integer.valueOf(num1);
//                        break;
//                    case "D":
//                        x -= Integer.valueOf(num1);
//                        break;
//                    default:
//                        break;
//                }
//            }
//        }
//        System.out.print(x+","+y);
//    }

//    正确答案，离用正则表达式，[0-9]{1,2}表示从0-9这些数字中匹配1到2个
   public static void main2(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String str=sc.nextLine();
            String[] A=str.split(";");
            int x=0,y=0;
            for(String string:A){
                if(string.charAt(0)=='D' && string.substring(1).matches("[0-9]{1,2}"))
                    x+=Integer.parseInt(string.substring(1));

                if(string.charAt(0)=='W' && string.substring(1).matches("[0-9]{1,2}"))
                    y+=Integer.parseInt(string.substring(1));

                if(string.charAt(0)=='S' && string.substring(1).matches("[0-9]{1,2}"))
                    y-=Integer.parseInt(string.substring(1));

                if(string.charAt(0)=='A' && string.substring(1).matches("[0-9]{1,2}"))
                    x-=Integer.parseInt(string.substring(1));

            }
            System.out.println(x+","+y);
        }

        sc.close();
    }

    /**
     * d44_1 骆驼命名法
     * 先按下划线分割字符串，再把除第一个单词以外的
     * 其他单词首字母大写后拼接到一起
     */
    public static void main3(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            String[] sss= s.split("_");
            StringBuilder sb = new StringBuilder();
            sb.append(sss[0]);
            for(int i = 1; i < sss.length; i++){
                String up = sss[i].substring(0,1).toUpperCase();
                sb.append(up);
                sb.append(sss[i].substring(1));
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * d44_2 【中级】单词倒排
     * 需要注意的是要把非字母的字符替换成空格
     */
    public static void main4(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String ss = sc.nextLine();
            StringBuffer s = new StringBuffer();
            for(int i = 0; i < ss.length(); i++){
                char c = ss.charAt(i);
                if(!isLetter(c)){
                    s.append(" ");
                }else{
                    s.append(c);
                }
            }
            Stack<String> stack = new Stack();
            String[] res = s.toString().split(" ");
            for(int i = 0; i < res.length; i++){
                stack.push(res[i]);
            }
            while(!stack.empty()){
                System.out.print(stack.pop()+" ");
            }
        }
    }
    public static boolean isLetter(char c){
        if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z'){
            return true;
        }
        return false;
    }

    /**
     * d40_1 删除公共字符_
     * 正则表达式[]
     */
    public static void main6(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            String ss = sc.nextLine();
            String parten = "["+ss+"]";
            System.out.println(s.replaceAll(parten,""));
        }
    }
    // 或者直接一个一个截取被删除的字符串，把所有在s中出现的都替换为空
    // 因为我们需要替换成空字符，因此也需要把ss拆分成字符串形式的，不能用charAt
    // 而是要用substring
    public static void main66(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            String ss = sc.nextLine();
            for(int i = 0; i < ss.length(); i++){
                s = s.replaceAll(ss.substring(i,i+1),"");
            }
            System.out.println(s);
        }
    }

    /**
     * d40_2 句子逆序
     */
    public static void main5(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            String[] res = s.split(" ");
            Stack<String> stack = new Stack();
            for(int i = 0; i < res.length; i++){
                stack.push(res[i]);
            }
            while(!stack.empty()){
                System.out.print(stack.pop() + " ");
            }
        }
    }
}



