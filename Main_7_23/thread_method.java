package com.Main_7_23;
class myThread extends Thread {
    @Override
    public void run() { }
}
public class thread_method {
    public static void main(String[] args) {

        Thread thread = new Thread();
        System.out.println(thread.getName()); // Thread-0
        thread.setName("线程A");
        System.out.println(thread.getName()); // 线程A
        // 构造方法设置线程名
        Thread thread1 = new Thread("线程2");
        System.out.println(thread1.getName()); // 线程2
        thread.start();
        thread1.start();
    }
}
