package org.jteodoro.concurrency.threads.guards;

import java.net.PortUnreachableException;
import java.time.LocalTime;

public class GuardTest {
    
    public static void main(String []  args) throws InterruptedException {
        // hwo to get a livelock!
        System.out.println(">>>>>> Started main " + LocalTime.now());

        Bus bus = new Bus();
        Producer producer = new Producer();
        producer.createMessageAfter(1000, "1.", bus);
        producer.createMessageAfter(2000, "2.", bus);
        producer.createMessageAfter(3000, "3.", bus);
        producer.createMessageAfter(4000, "4.", bus);
        producer.createMessageAfter(5000, "5.", bus);
        producer.createMessageAfter(6000, "6.", bus);
        producer.createMessageAfter(7000, "7.", bus);

        Consumer.consume(bus, Producer.message("1."));
        Consumer.consume(bus, Producer.message("2."));
        Consumer.consume(bus, Producer.message("3."));
        Consumer.consume(bus, Producer.message("4."));
        Consumer.consume(bus, Producer.message("5."));
        Consumer.consume(bus, Producer.message("6."));
        Consumer.consume(bus, Producer.message("7."));

        System.out.println(">>>>>> Finised main " + LocalTime.now());
    }
    
}