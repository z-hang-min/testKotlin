package com.tz.javalibrary;

/**
 * created by zm on 2023/2/16
 *
 * @Description:
 */
class HasInterruptException {
    public static void main(String[] args) throws InterruptedException {
        MyThres myThres = new MyThres();
        myThres.start();
        Thread.sleep(500);
        myThres.interrupt();
    }

}

class MyThres extends Thread {
    @Override
    public void run() {
        super.run();
        while (!isInterrupted()) {
            System.out.println("---" + getName()+"--"+ isInterrupted());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("InterruptedException---" + getName()+"--"+ isInterrupted());
            }
            System.out.println("isinterrupt---" + isInterrupted());
        }
        System.out.println("222isinterrupt---" + isInterrupted());
    }
}
