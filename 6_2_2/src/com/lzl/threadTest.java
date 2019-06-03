package com.lzl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
class myThread implements Runnable{
    @Override
    public void run() {
        System.out.println("主线程休眠开始");
        printTime();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程休眠结束");
        printTime();
    }
    public static void printTime(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = dateFormat.format(date);
        System.out.println(s);
    }
}
public class threadTest{

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开始");
        Thread thread = new Thread(new myThread());
        thread.start();
       // thread.join();
        System.out.println("主线程结束");
    }
}
//class myThread implements Runnable{
//    @Override
//    public void run() {
//        for(int i = 0; i < 555; i++){
//            Thread.yield();
//            Date date = new Date();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.println(simpleDateFormat.format(date));
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//public class threadTest extends Thread{
//    public static void main(String[] args) {
//        Thread thread = new Thread(new myThread());
//        thread.start();
//        Thread thread = new Thread("构造方法设置名字");
//        System.out.println(thread.getName());
//        thread.setName("新名字");
//        System.out.println(thread.getName());
//    }
//}
