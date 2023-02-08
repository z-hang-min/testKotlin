package com.tz.facetest.aboutt.asm;

/**
 * created by zm on 2023/2/3
 * 0-javac TestJvm.java编译成.class文件
 *01-javap -c TestJvm.class//反汇编
 * @Description:
 */
class TestJvm {
    public TestJvm() {

    }

    public static void main(String[] args) {
        TestJvm testJvm = new TestJvm();
        testJvm.work();
    }

    int work() {
        int x = 3;
        int y = 5;
        int z = (x + y) * 10;
        return z;

    }
}
