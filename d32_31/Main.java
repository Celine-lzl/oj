package d32_31;

import java.util.*;
public class Main{
    /**
     * d32_1 在霍格沃茨找零钱
     * 全部换成最小单位，再化成大单位的，注意运算时加括号保证运算顺序正确
     */
    public static void main1(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String pay = sc.next();
            String need = sc.next();
            String[] needy = need.split("\\.");
            String[] payy = pay.split("\\.");
            int sumn = (Integer.parseInt(needy[0])*17*29) + (Integer.parseInt(needy[1])*29) + Integer.parseInt(needy[2]);
            int sump = (Integer.parseInt(payy[0])*17*29) + (Integer.parseInt(payy[1])*29) + Integer.parseInt(payy[2]);
            int diff = sumn - sump;
            if(diff < 0){
                System.out.print("-");
                diff = -diff;
            }
            int golleon = diff / (17*29);
            int sickle = (diff - (golleon*17*29)) / 29;
            int knut = diff - (golleon*17*29) - (sickle*29) ;
            System.out.println(golleon + "." + sickle + "." + knut);
        }
    }
    /**
     * d31_1 说反话
     * 字符串逆序输出，没什么难度，注意最后一个单词后没有空格
     */
    public static void main2(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            String[] arr = s.split(" ");
            for(int i = arr.length-1; i > 0; i--){
                System.out.print(arr[i]+" ");
            }
            System.out.print(arr[0]);
        }
    }
    /**
     * d31_2 简单错误记录
     * 题目表述有问题，应该是输出最后8个
     */
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            //LinkedHashMap可以保证按照存入顺序
            Map<String, Integer> map = new LinkedHashMap<String, Integer>();
            int count = 0;
            while (sc.hasNextLine()) {
                String str_1 = sc.nextLine();
                //读取一行  存入一行至map中
                mapDispose(map, str_1);
            }
            // 输出后8个文件
            for (String st : map.keySet()) {
                count++;
                if (count > (map.size() - 8)) {
                    System.out.println(st + " " + map.get(st));
                }
            }
        }
        public static void mapDispose(Map<String, Integer> map, String str) {
            // "\\s" 匹配任何空白字符，包括空格、制表符、换页符等等
            String[] strArr = str.split("\\s+");
            // "\\\\"按照反斜杠分割
            String[] nameArr = strArr[0].split("\\\\");
            // 取得行号
            int num = Integer.parseInt(strArr[1]);
            // 取得文件名
            String name = nameArr[nameArr.length - 1];
            // 取最后有效的16个字符为最终文件名
            if (name.length() > 16) {
                name = name.substring(name.length() - 16);
            }
            // 将文件名和行号组成字符串 作为map的key值存放
            String key = name + " " + num;
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
    }


