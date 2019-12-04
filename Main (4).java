package day26;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {


    /**
     * d26_2 快到碗里来
     * 第一题是变态跳台阶
     */
    public static void isIn() {
        Scanner in=new Scanner(System.in);
        while(in.hasNext()){
            BigDecimal m = in.nextBigDecimal();
            BigDecimal n = in.nextBigDecimal();
            BigDecimal pi = new BigDecimal("6.28");
            BigDecimal l = n.multiply(pi);
            if(l.compareTo(m) == 1){
                System.out.println("Yes");
            }
            else{
                System.out.println("No");
            }
        }
    }

    /**
     * j_d24_1 小易的升级之路
     */
        public static void main(String[] args){
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNext()){
                int n = scanner.nextInt();
                int a = scanner.nextInt();
                int[] data = new int[n];
                for(int i = 0; i < n; i++){
                    data[i] = scanner.nextInt();
                    if(a >= data[i]){
                        a += data[i];
                    }else{
                        a += Gcd(data[i],a);
                    }
                }
                System.out.println(a);
            }
        }
        public static int Gcd(int a,int b){
            while(a % b != 0){
                int c = a % b;
                a = b;
                b = c;
            }
            return b;
        }
        
}
