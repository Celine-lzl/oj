package com.Main_7_23;

import java.util.Scanner;

//package com.Main_7_23;
//
//import java.util.*;
//
//public class test {
//    public static void main(String[] args) {
////        List<String> list = new ArrayList<>();
////        Map<String, Integer> map = new HashMap<>();
////        map.put("a", 1);
////        map.put("d", 2);
////        map.put("c", 3);
////        map.put("b", 4);
////        Set<String> set = map.keySet();
////        Iterator<String> iterator = set.iterator();
////        list.addAll(set);
////        System.out.println(set);
////        List<Integer> list1 = new ArrayList<>(10);
////        list1.add(1);
////        list1.add(2);
////        list1.add(3);
////        list1.add(4);
////        list1.add(5);
////        list1.add(6);
////        list1.add(7);
////        list1.add(8);
////        list1.add(9);
////        list1.add(10);
////        Iterator iterator1 = list1.iterator();
////        while(iterator1.hasNext()){
////            if(iterator1.equals(2)){
////                iterator1.remove();
////                continue;
////            }
////            System.out.println(iterator.next());
////        }
//////        System.out.println(list1);
////    }
////}
////        int[] a = new int[]{3,5,1,99,24,0,523,21,1};
////        Arrays.sort(a);
////        System.out.println(a[a.length-1]);
////        System.out.println("----------------");
////        for(int i = 0; i < a.length; i++){
////            System.out.println(a[i]);
////        }
////        int[] b = new int[5];
////        System.out.println(b[1] += 1);
//
//        List<String> list = new ArrayList<>();
//        list.add("HI");
//        list.add("HELLO");
//        list.add("HOW");
//        list.add("b");
//        Vector<String> vector = new Vector<>();
//        vector.add("a");
//        vector.add("b");
//        vector.add("c");
//        vector.add("d");
//        vector.add("e");
//        Enumeration<String> enumeration = vector.elements();
//        while(enumeration.hasMoreElements()){
//            System.out.println(enumeration.nextElement());
//        }
//        Map<Integer,String> map = new HashMap<>();
//        map.put(1,"a");
//        map.put(2,"c");
//        map.put(3,"b");
////        System.out.println(map+"-------");
////        System.out.println(map.get(1));
////        System.out.println("===========");
////        System.out.println(map.values());
//        int[] data = new int[]{10,6,14,856,241};
//        Arrays.sort(data);
//        for(int i = 0; i < data.length; i++){
//            System.out.print(data[i]+" ");
//        }
//    }
//}
//
public class test{
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



