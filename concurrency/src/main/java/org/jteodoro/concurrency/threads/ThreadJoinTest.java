package org.jteodoro.concurrency.threads;

import java.time.LocalTime;

public class ThreadJoinTest {

    private static Runnable sleepy(long milis, String mark) {
        return () -> {
            try {
                System.out.println(mark + " Sleeping for " + milis);
                Thread.sleep(milis);
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
        System.out.println(mark + " Started at " + LocalTime.now());
    }

    public static void exitWithWaitingASingleThread(String mark) throws InterruptedException {
        System.out.println(mark + " Started at " + LocalTime.now());
        Thread st1 = new Thread(sleepy(1000, mark + "1."));
        st1.start();
        Thread st2 = new Thread(sleepy(2000, mark + "2."));
        st2.start();
        Thread st3 = new Thread(sleepy(3000, mark + "3."));
        st3.start();
        Thread st4 = new Thread(sleepy(4000, mark + "4."));
        st4.start();
        System.out.println(mark + " Started at " + LocalTime.now());

        st1.join();
        System.out.println(mark + " run only after "+ mark + "1." +" finished");
    }

    public static void exitWithWaitingAllThreadsStartingFromEarlyOne(String mark) throws InterruptedException {
        System.out.println(mark + " Started at " + LocalTime.now());
        Thread st1 = new Thread(sleepy(1000, mark + "1."));
        st1.start();
        Thread st2 = new Thread(sleepy(2000, mark + "2."));
        st2.start();
        Thread st3 = new Thread(sleepy(3000, mark + "3."));
        st3.start();
        Thread st4 = new Thread(sleepy(4000, mark + "4."));
        st4.start();
        System.out.println(mark + " Started at " + LocalTime.now());
        st1.join();
        st2.join();
        st3.join();
        st4.join();
        System.out.println(mark + " run only after "+ mark + "1." +" finished");
    }

    public static void exitWithWaitingAllThreadsStartingFromLaterOne(String mark) throws InterruptedException {
        System.out.println(mark + " Started at " + LocalTime.now());
        Thread st1 = new Thread(sleepy(1000, mark + "1."));
        st1.start();
        Thread st2 = new Thread(sleepy(2000, mark + "2."));
        st2.start();
        Thread st3 = new Thread(sleepy(3000, mark + "3."));
        st3.start();
        Thread st4 = new Thread(sleepy(4000, mark + "4."));
        st4.start();
        System.out.println(mark + " Started at " + LocalTime.now());
        st4.join();
        st3.join();
        st2.join();
        st1.join();
        System.out.println(mark + " run only after "+ mark + "1." +" finished");
    }

    public static void main(String []  args) throws InterruptedException {
        System.out.println(">>>>>> Started main " + LocalTime.now());

        exitWithNoWaiting("1.");
        System.out.println(">>>>>> run after "+ "1.");
        exitWithWaitingASingleThread("2.");
        System.out.println(">>>>>> run after "+ "2.");
        exitWithWaitingAllThreadsStartingFromEarlyOne("3.");
        System.out.println(">>>>>> run after "+ "3.");
        exitWithWaitingAllThreadsStartingFromLaterOne("4.");
        System.out.println(">>>>>> run after "+ "4.");

        System.out.println(">>>>>> Finished main " + LocalTime.now());
    }
    
}
