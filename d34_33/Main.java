//package d34_33;
//
//import java.util.Iterator;
//import java.util.LinkedHashSet;
//import java.util.Map;
//import java.util.Scanner;
//public class Main {
//    /**
//     * d34_1 Broken Keyboard
//     * 遍历应该输出的那个字符串，判断实际输出的字符串中是否含有该
//     * 字符，若不含有，加入到LinkedHashSet
//     * LinkedHashSet能去重也能按照输入顺序输出
//     */
//    public static void main1(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            String should = sc.next().toUpperCase();
//            String real = sc.next().toUpperCase();
//            LinkedHashSet<Character> set = new LinkedHashSet();
//            for (int i = 0; i < should.length(); i++) {
//                char c1 = should.charAt(i);
//                if (!real.contains(String.valueOf(c1))) {
//                    set.add(c1);
//                }
//            }
//            Iterator iterator = set.iterator();
//            while (iterator.hasNext()) {
//                System.out.print(iterator.next());
//            }
//        }
//    }
//
//    /**
//     * d34_2 球的半径和体积
//     * arccos(x) = Math.acos(x); 别的同理
//     * 限制小数点后数的位数：System.out.format("%.3f %.3f",r,V);
//     */
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            double x0 = sc.nextDouble();
//            double y0 = sc.nextDouble();
//            double z0 = sc.nextDouble();
//            double x1 = sc.nextDouble();
//            double y1 = sc.nextDouble();
//            double z1 = sc.nextDouble();
//            double r1 = (x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1) + (z0 - z1) * (z0 - z1);
//            double r = Math.sqrt(r1);
//            double V = (4.0 / 3) * Math.acos(-1) * Math.pow(r, 3);
//            System.out.format("%.3f %.3f", r, V);
//            double a = 3.555678;
//            double b = 3.2412;
//            System.out.format("%.3f %.3f", a, b);
//        }
//    }
//
//    /**
//     * d33_2 上楼梯
//     * 动态规划。变态青蛙跳台阶
//     */
//    public int countWays(int n) {
//        // 第一阶有一种方法
//        // 第二阶有两种方法，一步一步 or 一次两步
//        // 第三阶有四种方法，1+1+1 or 1 + 2 or 2 + 1 or 3
//        long[] pre = {1, 2, 4};
//        if(n<=0) return 0;
//        else if(n<=3) return (int)pre[n-1];
//        else{
//        for(int i=4; i<=n; i++){
//        long tmp = (pre[0] + pre[1] + pre[2]) % 1000000007;
//        pre[0] = pre[1];
//        pre[1] = pre[2];
//        pre[2] = tmp;
//        }
//        }
//    return (int)pre[2];
// }
//}
