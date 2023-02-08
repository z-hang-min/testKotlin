package com.tz.facetest;

import org.junit.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.commons.Method;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * created by zm on 2023/2/4
 *
 * @Description:
 */
public class ASMUnitTest {
    @Test
    public void test() {
        try {
            FileInputStream a =
//                    new FileInputStream("/Users/zhangmin/Desktop/tz2022/coderes/testKotlin/facetest/src/main/java/com/tz/facetest/aboutt/asm/InjectTest.class");
                    new FileInputStream("/Users/zhangmin/Desktop/tz2022/coderes/testKotlin/facetest/build/intermediates/javac/debug/classes/com/tz/facetest/aboutt/asm/InjectTest.class");
            ClassReader classReader = new ClassReader(a);

            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);


            //开始插桩
            classReader.accept(new MyClassVisitor(Opcodes.ASM7, classWriter), ClassReader.EXPAND_FRAMES);
            //执行插桩之后的字节码数据写入一个文件
            byte[] byres = classWriter.toByteArray();
            //最后生成新的文件
            FileOutputStream outputStream = new FileOutputStream("/Users/zhangmin/Desktop/tz2022/coderes/testKotlin/facetest/src/main/java/com/tz/facetest/aboutt/asm/InjectTest2.class");
            outputStream.write(byres);
            outputStream.close();
            a.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyClassVisitor extends ClassVisitor {

        protected MyClassVisitor(int api) {
            super(api);
        }

        protected MyClassVisitor(int api, ClassVisitor classVisitor) {
            super(api, classVisitor);
        }

        /**
         * 方法扫描
         *
         * @author zm
         * create at 2023/2/4 11:46
         **/
        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
            System.out.println(name);
            return new MyMethondVisitor(api, methodVisitor, access, name, descriptor);

        }
    }

    /**
     * 分析方法
     *
     * @author zm
     * create at 2023/2/4 11:44
     **/
    static class MyMethondVisitor extends AdviceAdapter {


        protected MyMethondVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
            super(api, methodVisitor, access, name, descriptor);
        }

        int s;

        @Override
        protected void onMethodEnter() {
            super.onMethodEnter();
            if (!inject)
                return;
            //  INVOKESTATIC java/lang/System.currentTimeMillis ()J
            //    LSTORE 1
            invokeStatic(Type.getType("L java/lang/System")
                    , new Method("currentTimeMillis", "()J"));
            s = newLocal(Type.LONG_TYPE);
            storeLocal(s);
        }

        int e;

        @Override
        protected void onMethodExit(int opcode) {
            super.onMethodExit(opcode);
            if (!inject)
                return;

            //   long tim2 = System.currentTimeMillis();
            //        System.out.println("execute time is " + (tim2 - time1) + "ms");
            //    INVOKESTATIC java/lang/System.currentTimeMillis ()J
            invokeStatic(Type.getType("Ljava/lang/System;")
                    , new Method("currentTimeMillis", "()J"));
            e = newLocal(Type.LONG_TYPE);
            //    LSTORE 3
            storeLocal(e);

            //GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
            getStatic(Type.getType("Ljava/lang/System;"), "out", Type.getType("L java/io/PrintStream;"));
//NEW java/lang/StringBuilder
            newInstance(Type.getType("Ljava/lang/StringBuilder;"));
            //    DUP
            dup();
            //    INVOKESPECIAL java/lang/StringBuilder.<init> ()V

            invokeConstructor(Type.getType("Ljava/lang/StringBuilder;"), new Method("<init>", "()V"));
//    LDC "execute time is "
            visitLdcInsn("execute time =");
//    INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
            invokeVirtual(Type.getType("Ljava/lang/StringBuilder;"),
                    new Method("append",
                            "(Ljava/lang/String;)Ljava/lang/StringBuilder;"));
//    LLOAD 3
            loadLocal(e);
            //    LLOAD 1
            loadLocal(s);
            //    LSUB
            math(SUB, Type.LONG_TYPE);
//    INVOKEVIRTUAL java/lang/StringBuilder.append (J)Ljava/lang/StringBuilder;
            invokeVirtual(Type.getType("Ljava/lang/StringBuilder;"),
                    new Method("append", "(J)Ljava/lang/StringBuilder;"));
//    LDC "ms"
            visitLdcInsn("ms");
//    INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
            invokeVirtual(Type.getType("Ljava/lang/StringBuilder;"),
                    new Method("append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;"));

//    INVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
            invokeVirtual(Type.getType("Ljava/lang/StringBuilder;"),
                    new Method("toString", "()Ljava/lang/String;"));

//    INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
            invokeVirtual(Type.getType("Ljava/io/PrintStream;"), new Method("println", "(Ljava/lang/String;)V"));

//   L7

        }

        boolean inject;

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            System.out.println(descriptor);
            if (descriptor.equals("Lcom/tz/facetest/aboutt/asm/ASMTEST;")) {
                inject = true;
            } else {
                inject = false;
            }
            return super.visitAnnotation(descriptor, visible);
        }
    }

}
