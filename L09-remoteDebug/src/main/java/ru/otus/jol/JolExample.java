package ru.otus.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

//VM option: -Djdk.attach.allowAttachSelf

//-XX:ObjectAlignmentInBytes=256
//-XX:-UseCompressedOops
//-XX:-UseCompressedClassPointers
public class JolExample {
    public static void main(String[] args) {
        new JolExample().demo();
    }

    public void demo() {

        //Какой будет результат?
       // System.out.println("boolean:" + VM.current().sizeOfField("boolean"));
        //Как этот результат получился?


//        System.out.println(VM.current().details());
//        System.out.println("sizeOfField:" + VM.current().sizeOfField(""));

        //Layout объекта
        //objectLayout();

         //       System.out.println(ClassLayout.parseClass(TestB.class).toPrintable());
        //        System.out.println(ClassLayout.parseClass(TestB2.class).toPrintable());
        //        System.out.println(ClassLayout.parseClass(TestB4.class).toPrintable());
        //        System.out.println(ClassLayout.parseClass(TestInt.class).toPrintable());

        //       System.out.println(ClassLayout.parseClass(TestInt2.class).toPrintable());
        //        System.out.println(ClassLayout.parseClass(TestInt4.class).toPrintable());

        //       System.out.println(ClassLayout.parseClass(Mix.class).toPrintable());
        //       System.out.println(ClassLayout.parseClass(TestL.class).toPrintable());
    }

//https://github.com/openjdk/jol/blob/master/jol-samples/src/main/java/org/openjdk/jol/samples/JOLSample_22_Promotion.java

    static volatile Object sink;
    private void objectLayout() {
        TestB testB = new TestB();
        System.out.println(ClassLayout.parseInstance(testB).toPrintable());

        long lastAddr = VM.current().addressOf(testB);
        int moves = 0;
        for (int idx = 0; idx < 100000; idx++) {
            long cur = VM.current().addressOf(testB);
            if (cur != lastAddr) {
                moves++;
                System.out.printf("*** Move %2d, object is at %x%n%n", moves, cur);
                System.out.println(ClassLayout.parseInstance(testB).toPrintable());
                lastAddr = cur;
            }

            // make garbage
            for (int c = 0; c < 1000; c++) {
                sink = new Object();
            }
        }

        long finalAddr = VM.current().addressOf(testB);
        System.out.printf("*** Final object is at %x%n", finalAddr);
        System.out.println(ClassLayout.parseInstance(testB).toPrintable());


/*
        System.out.println(ClassLayout.parseInstance(testB).toPrintable());

        System.gc();
        makeGarbage();
        sleep();
        System.out.println(ClassLayout.parseInstance(testB).toPrintable());

        System.gc();
        makeGarbage();
        sleep();

        System.out.println(ClassLayout.parseInstance(testB).toPrintable());
*/
    }

    public class TestB {
        boolean valBool;
    }

    public class TestB2 {
        boolean valBool1;
        boolean valBool2;
    }

    public class TestB4 {
        boolean valInt1;
        boolean valInt2;
        boolean valInt3;
        boolean valInt4;
    }

    public class TestInt {
        int valInt1;
    }

    public class TestInt2 {
        int valInt1;
        int valInt2;
    }

    public class TestInt4 {
        int valInt1;
        int valInt2;
        int valInt3;
        int valInt4;
    }

    public class Mix {
        boolean boolVal;
        int intVal;
        boolean isBoolVal;
    }

    public class TestL {
        long l1;
        long l2;
    }

    private void makeGarbage() {
        for (int idx = 0; idx < Integer.MAX_VALUE; idx++) {
            Object obj = new String[10000];
        }
    }


    private static void sleep() {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
