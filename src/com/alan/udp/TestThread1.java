package com.alan.udp;

public class TestThread1 extends Thread{

    @Override
    public void run() {
        //run方法线程池
        for (int i = 0; i < 20; i++) {
            System.out.println("看看多线程，第" + i + "行");
        }
    }

    public static void main(String[] args) {
        TestThread1 t1 = new TestThread1();
         t1.start();

        for (int i = 0; i < 20; i++) {
            System.out.println("我在学习" + i);
        }
    }
}
