package com.tz.javalibrary;

/**
 * created by zm on 2023/2/17
 *
 * @Description:
 */
class VolatileTest {
    private  static int num = 52;
    private volatile static boolean ready;

    static class Thread1 extends Thread {
        @Override
        public void run() {
            super.run();

            System.out.println("running");
            System.out.println("num1=" + num);
            while (!ready) ;
            System.out.println("num=" + num);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        thread1.start();
        Thread.sleep(1000);
        num = 55;
        ready = true;
        Thread.sleep(5000);
        System.out.println("main isout");

    }

}
