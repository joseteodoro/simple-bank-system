package org.jteodoro.concurrency.threads.guards;

import java.util.Objects;
import java.util.Random;

public class Consumer {
    
    private static boolean shouldConsume(Bus bus, String expected) {
        String message = bus.tryRecieve();
        return Objects.nonNull(message) && expected.equals(message);
    }

    private static Runnable consumeMessage(Bus bus, String expected) {
        return () -> {
            try {
                while(!shouldConsume(bus, expected)) {
                    Thread.sleep(new Random(expected.hashCode()).nextInt(5000));
                }
                String message = bus.tryRecieve();
                System.out.println("Consuming message: " + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    public static void consume(Bus bus, String expected) {
        Thread st1 = new Thread(consumeMessage(bus, expected));
        st1.start();
    }
}
