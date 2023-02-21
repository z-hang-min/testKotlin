package com.tz.javalibrary;

/**
 * created by zm on 2023/2/17
 *
 * @Description:
 */
class SynTest {
    public int count = 0;

    public void setCount(int count) {
        this.count = count;
    }

    public void inCount() {
        synchronized (this) {
            this.count++;

        }
    }

    public synchronized void inCount2() {
        this.count++;
    }
}

class ThredTest extends Thread {
    public ThredTest(SynTest synTest) {
        mSynTest = synTest;
    }

    SynTest mSynTest;


    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            mSynTest.inCount();
        }

    }
}

class Test {
    public static void main(String[] args) throws InterruptedException {
        SynTest synTest = new SynTest();
        ThredTest thredTest = new ThredTest(synTest);
        ThredTest thredTest2 = new ThredTest(synTest);
        thredTest.start();
        thredTest2.start();
        Thread.sleep(150);
        System.out.println("count==" + synTest.count);

    }
}