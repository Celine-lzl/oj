package com.lzl;

//public class Test {
//    int length = 0;
//    public void stackTest(){
//        length++;
//        stackTest();
//    }
//    public static void main(String[] args) {
//        Test test = new Test();
//        try{
//            test.stackTest();
//        }catch(Throwable e){
//            e.printStackTrace();
//            System.out.println("栈深度为："+test.length);
//        }
//    }
//}

// 对象自我拯救
//public class Test{
//    private static Test test; // 静态对象
//    public void iaAlive(){
//        if(test != null){
//            System.out.println("I am Alive ");
//        }
//    }
//    @Override
//    protected void finalize() throws Throwable {  //覆写了finalize且没被调用过，JVM开始调用，将对象放入F-queue
//        super.finalize();
//        System.out.println("finalize method exectues");
//        test = this;  //给当前对象分配了个静态变量对象的引用，即连接GC ROOTS
//    }
//    public static void main(String[] args) {
//        test = new Test(); //可达
//        test = null;  //不可达
//        System.gc();  //垃圾回收，对test进行第一次标记，并筛选
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if(test != null){
//            test.iaAlive();
//        }else{
//            System.out.println("I am dead");
//        }
//        //----------------分界线
//// 第二遍操作
//        test = null;
//        System.gc();
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if(test != null){
//            test.iaAlive();
//        }else{
//            System.out.println("I am dead");
//        }
//    }
//}

