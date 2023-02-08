package com.tz.facetest.aboutt.asm;

/**
 * created by zm on 2023/2/4
 *
 * @Description:
 */
class InjectTest {
    public InjectTest() {
    }

    @ASMTEST
    public static void main(String[] args) {
//        long time1 = System.currentTimeMillis();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        long tim2 = System.currentTimeMillis();
//        System.out.println("execute time is " + (tim2 - time1) + "ms");

    }

    void method() {

    }
}
