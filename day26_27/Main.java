package JDay.day26_27;

import java.util.*;
public class Main{

    /**
     * 整数与IP地址间的转换
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String ip = sc.nextLine();
            String num = sc.nextLine();
            System.out.println(ipToNum(ip));
            System.out.println(numToIp(num));
        }
    }
    // ip地址转为数字
    private static long ipToNum(String ip){
        String[] ips = ip.split("\\.");//使用.分割
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < ips.length; i++){
            sb.append(binaryString(ips[i]));//10进制数转二进制字符串
        }
        return Long.parseLong(sb.toString(),2);
    }
    private static String binaryString(String s){//十进制转换为8位二进制
        StringBuffer sb = new StringBuffer();
        int num = Integer.parseInt(s);
        int k = 1<<7;
        for(int i = 0; i < 8; i++){
            int flag = (num & k) == 0? 0 : 1;
            sb.append(flag);
            num = num << 1;
        }
        return sb.toString();
    }

    // 数字转为ip地址
    public static String numToIp(String n){
        long num = Long.parseLong(n);
        StringBuffer sb = new StringBuffer();
        // &255是为了只取低8位
        sb.append(num >> 24).append(".").append((num >> 16) & 255)
                .append(".").append((num >> 8) & 255).append(".")
                .append(num & 255);
        return sb.toString();
    }

    /**
     * d27_1 找X
     */
    public static void main6(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList();
        // 此处也可以用普通数组data来保存数字，最后要用List的indexOf方法时
        // 使用Arrays.asList(data) 把普通数组变成List
        for(int i = 0; i < n; i++){
            list.add(sc.nextInt());
        }
        int target = sc.nextInt();
        System.out.println(list.indexOf(target));
        // System.out.println(Arrays.asList(data).indexOf(target));
    }
    // ****************************************************************
    public static void main5(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            LinkedHashMap<Integer,Integer> list = new LinkedHashMap();
            for(int i = 0; i < n; i++){
                list.put(sc.nextInt(),i);
            }
            int key = sc.nextInt();
            if(list.get(key) != null){
                System.out.println(list.get(key));
            }else{
                System.out.println(-1);
            }
        }
    }
    // ***********************************************
    public static void main4(String[] args){
        int re = res();
        System.out.println(re);
    }
    public static int res(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] data = new int[n];
        for(int i = 0; i < n; i++){
            data[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        for(int i = 0; i < n; i++){
            if(data[i] == target){
                return i;
            }
        }
        return -1;
    }

    /**
     * d26_2计票统计
     * 这道题坑的地方在于顺序，输出必须按输入顺序，因此不用hashmap而是linkedhashmap
     * 并且，Invalid的统计，需要单独统计，如果把它放入map集合中，会打乱输入顺序
     */
    public static void main3(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int people = sc.nextInt(); // 竞选人个数
            Map<String,Integer> map = new LinkedHashMap();
            for(int i = 0; i < people; i++){
                map.put(sc.next(),0);
            }
            int ticket = sc.nextInt();  // 投的票数
            int invalid = 0;
            for(int i = 0; i < ticket; i++){
                String tmp = sc.next();
                if(map.containsKey(tmp)){
                    map.put(tmp,map.get(tmp)+1);
                }else{
                    invalid++;
                }
            }
            // 输出map中的key和value
//           注释掉的是标准写法，下面的for循环为简便写法
//            Set<Map.Entry<String,Integer>> set = map.entrySet();
//            Iterator<Map.Entry<String,Integer>> iterator = set.iterator();
//            while(iterator.hasNext()){
//                Map.Entry<String,Integer> entry = iterator.next();
//                System.out.println(entry.getKey()+" : "+entry.getValue());
//            }
            for(Map.Entry entry : map.entrySet()) {
                System.out.println(entry.getKey()+" : "+entry.getValue());
            }
            System.out.println("Invalid : " + invalid);
        }
    }

    /**
     * d26_1数字之和
     */
    public static void main2(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            System.out.print(getSum(n)+" ");
            int nf = n*n;
            System.out.println(getSum(nf));
        }
    }
    public static int getSum(int n){
        int sum = 0;
        while(n / 10 != 0){
            sum += n % 10;
            n /= 10;
        }
        sum += n;
        return sum;
    }
}