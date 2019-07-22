package com.lzl;

//public class testIsInterrupted {
//    static class MyThread implements Runnable{
//        private boolean flag = true;
//        @Override
//        public void run(){
//            int i = 1;
//            while(true){
//                try{
//                    boolean bool = Thread.currentThread().isInterrupted();  //判断是否被打断
//                    System.out.println(Thread.currentThread().getName()+"第"+i+"次执行");
//                    System.out.println(bool);
//                    if(bool){
//                        System.out.println("线程退出");
//                        break;
//                    }
//                    i++;
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    System.out.println("异常抛出，线程停止");
//                    boolean bool = Thread.currentThread().isInterrupted();
//                    System.out.println("catch块中中断状态为：" + bool);
//                    return;
//                }
//            }
//        }
//    }
//        public static void main(String[] args) {
//            MyThread myThread = new MyThread();
//            Thread thread = new Thread(myThread,"子线程A");
//            thread.start();
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            thread.interrupt();
//        }
//
//}
//
//class MyThread implements Runnable{
//    private int ticket = 100;
//    @Override
//    public void run(){ //三个线程可以同时进入run()方法和for循环
//        for(int i = 0; i<100; i++){
//            //同步代码块
//            synchronized (this) {  //加锁操作，同一时刻只有一个线程进入同步代码块，
//
//                //--------------------------------------------------------------------
//                if (ticket > 0) {
//                    try {
//                        //模拟网络延迟
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread().getName() + "还剩下" + this.ticket-- + "张票");
//                }
//                //--------------------------------------------------------------------
//            }
//        }
//    }
//}
//public class testIsInterrupted{
//    public static void main(String[] args) {
//        MyThread mt = new MyThread();
//        Thread th1 = new Thread(mt,"黄牛A");
//        Thread th2 = new Thread(mt,"黄牛B");
//        Thread th3 = new Thread(mt,"黄牛c");
//        th1.start();
//        th2.start();
//        th3.start();
//    }
//}
class Sync{  //普通类
    //线程1进入testA方法，线程一仍在执行
    public synchronized void testA(){
        if(Thread.currentThread().getName().equals("A")){ //判断线程名字是否为A
            while(true){}
        } //线程1一直在执行
    } //普通同步方法，所得是当前Sync对象
    //线程2能否进入testB()方法---不能，得等A释放了锁才能进入，因为A和B共享一个对象Sync

    public synchronized void testB(){
        if(Thread.currentThread().getName().equals("B")){
            System.out.println("线程B打印此方法");
        }
    }
}
class MyThread implements Runnable{
    public MyThread(Sync sync) {
        this.sync = sync;
    }
    private Sync sync;
    @Override
    public void run(){
        this.sync.testA();
        this.sync.testB();
    }
}
public class testIsInterrupted {
    public static void main(String[] args) {
        Sync sync = new Sync();
        MyThread myThread = new MyThread(sync);
        Thread threadA = new Thread(myThread, "A");
        Thread threadB = new Thread(myThread, "B");
        threadA.start();
        try {
            Thread.sleep(100);//确保A先启动
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();
    }
}