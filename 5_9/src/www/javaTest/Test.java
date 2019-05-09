package www.javaTest;


//class MyThread implements Runnable{
//    //锁对象
//    private Lock lock = new ReentrantLock();
//    private int ticket = 500;
//    @Override
//    public void run(){
//        for(int i = 0; i<500; i++){
//            try{
//                lock.lock();
//                if(ticket > 0){
//                    System.out.println(Thread.currentThread().getName()+
//                            "还剩下"+this.ticket--+"张票");
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//            }finally {
//                //解锁放在finally块中，不管有没有异常，都会解锁
//                lock.unlock();
//            }
//        }
//    }
//}
//public class Test{
//    public static void main(String[] args) {
//        MyThread mt = new MyThread();
//        Thread threadA = new Thread(mt,"黄牛A");
//        Thread threadB = new Thread(mt,"黄牛B");
//        Thread threadC = new Thread(mt,"黄牛C");
//        threadA.start();
//        threadB.start();
//        threadC.start();
//    }
//}