package org.jteodoro.concurrency.threads;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class WaitTest {

    private static AtomicInteger joy = new AtomicInteger(0);
    
    private static Runnable sleepy(long milis, String mark) {
        return () -> {
            try {
                System.out.println(mark + " Sleeping for " + milis);
                Thread.sleep(milis);
                System.out.println(mark + " getting value " + joy.getAndIncrement());
                System.out.println(mark + " Resuming after " + milis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    public static void exitWithNoWaiting(String mark) {
        System.out.println(mark + " Started at " + LocalTime.now());
        Thread st1 = new Thread(sleepy(1000, mark + "1."));
        st1.start();
        Thread st2 = new Thread(sleepy(2000, mark + "2."));
        st2.start();
        Thread st3 = new Thread(sleepy(3000, mark + "3."));
        st3.start();
        Thread st4 = new Thread(sleepy(4000, mark + "4."));
        st4.start();
        System.out.println(mark + " finished at " + LocalTime.now());
    }

    private static Runnable guarded(String mark, Object obj) {
        return () -> {
            while(joy.get() < 4 ) {
                try {
                    obj.wait(); // can be only used as this.wait() // otherwise get the "current thread is not owner" error
                    // obj.waitFor // is only available for `Process` objects
                    System.out.println(mark + " finished at " + LocalTime.now());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
    }

    public static void waitingFor4(String mark) throws InterruptedException {
        Object obj = new Object();
        System.out.println(mark + " Started at " + LocalTime.now());
        Thread st1 = new Thread(guarded(mark + "1.", obj));
        st1.start();
        st1.join();
        System.out.println(mark + " Finished at " + LocalTime.now());
    }

    public static void main(String []  args) throws InterruptedException {
        System.out.println(">>>>>> Started main " + LocalTime.now());

        exitWithNoWaiting("1.");
        System.out.println(">>>>>> run after "+ "1.");
        waitingFor4("2.");
        System.out.println(">>>>>> run waiting "+ "2.");

        System.out.println(">>>>>> Finised main " + LocalTime.now());
    }
}
