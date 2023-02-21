package com.tz.javalibrary;

import java.util.concurrent.Callable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


/**
 * created by zm on 2023/2/16
 *
 * @Description:
 */
class TestThread {
    public static void main(String[] args) throws Exception {
////        System.out.println("hello---" + Thread.currentThread().getName());
//        Thread threadRun = new Thread(new MyThread());
////
////
        CallThread callThread = new CallThread("error");
//        FutureTask<String> futureTask = new FutureTask(callThread);
//        new Thread(futureTask).start();
//        String ss = futureTask.get();
//        System.out.println(ss);
//
//        Thread thread = new ExThread();
//
//        ExecutorService service = Executors.newFixedThreadPool(3);
////        for (int i = 0; i < 15; i++) {
//        Future<?> result = service.submit(futureTask);
//        System.out.println("result==" + result);
////        }
//        result.get();
//        futureTask.get();
        ExThread exThread=new ExThread();
        exThread.interrupt();
        exThread.start();
        System.out.println("1--"+System.currentTimeMillis());
//        Thread.sleep(2000);
        System.out.println("2--"+System.currentTimeMillis());
    }

}

class ExThread extends Thread {

    @Override
    public void run() {
        super.run();
        synchronized (this) {
            try {
                wait(2000);
                                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(System.currentTimeMillis() + "---ExThread==threadName--" + Thread.currentThread().getName());

        }

    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println("MyThread==threadName--" + Thread.currentThread().getName());
    }
}

class CallThread implements Callable<String> {
    String error;

    public CallThread(String error) {
        this.error = error;
    }

    @Override
    public String call() throws Exception {
        System.out.println("CallThread==threadName--" + Thread.currentThread().getName());
        Thread.sleep(5000);//阻塞5s
        return error;
    }
}
