package com.tz.javalibrary;

/**
 * created by zm on 2023/2/16
 *
 * @Description:
 */
class TestInterruptThread {
    public static void main(String[] args) throws InterruptedException {
        MyThread2 myThread2 = new MyThread2();
        myThread2.start();
//        myThread2.wait(100);
        Thread.sleep(10);
        myThread2.interrupt();
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName() + "1 isInterrupted" + isInterrupted());
        System.out.println(Thread.currentThread().getName() + "2 isInterrupted" + isInterrupted());
        while (!Thread.interrupted()) {
            System.out.println(Thread.currentThread().getName() + "3 isInterrupted" + isInterrupted());

        }
        System.out.println(Thread.currentThread().getName() + "4 isInterrupted" + isInterrupted());
    }
}
