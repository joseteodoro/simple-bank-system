package org.jteodoro.concurrency.threads.guards;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer {

    public static String message( String mark) {
        return "message sent by" + mark;
    }
    
    public void createMessageAfter(int milis, String mark, Bus bus) {
        Action action = () -> {
            bus.put(message(mark));
        };
        Thread st1 = new Thread(WaitForTime.sleepy(milis, mark + "1.", action));
        st1.start();
    }

}
