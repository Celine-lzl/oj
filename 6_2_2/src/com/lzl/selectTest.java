package com.lzl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
public class selectTest {
    public static void main(String[] args) {
        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");
        operator(a,b);
        System.out.println(a+","+b);
    }
    public static void operator(StringBuffer x,StringBuffer y){
        x.append(y);
        y = x;
    }
//    public static void main(String[] args) {
//        Thread t = new Thread() {
//            public void run() {
//                pong();
//            }
//        };
//        t.start();
//        System.out.println("ping");
//    }
//    static void pong(){
//        System.out.println("pong");
//    }
//        Map<String,String> map = new HashMap<>();
//        map.put("null",null);
//        map.put("null","kack");
//        System.out.println(map.size());
//        map.put("aha","wi");
//        System.out.println(map.size());
//        String s = "hello";
//        s.toUpperCase();
//        System.out.println(s+"1");
//        System.out.println(s.toUpperCase()+"!!!");
//        String x = "fmn";
//        System.out.println(x);
//        x.toUpperCase();
//        System.out.println(x);
//        String y = x.replace('f','F');
//        y = y + "wxy";
//        System.out.println(y);

    }

