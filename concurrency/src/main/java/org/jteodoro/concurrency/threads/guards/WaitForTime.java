package org.jteodoro.concurrency.threads.guards;

import java.util.function.Consumer;

public class WaitForTime {
    
    public static Runnable sleepy(long milis, String mark, Action action) {
        return () -> {
            try {
                System.out.println(mark + " Sleeping for " + milis);
                Thread.sleep(milis);
                action.act();
                System.out.println(mark + " Resuming after " + milis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

}
