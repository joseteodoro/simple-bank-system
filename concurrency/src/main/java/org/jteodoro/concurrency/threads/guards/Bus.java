package org.jteodoro.concurrency.threads.guards;

public class Bus {

    private String message = null;

    private boolean empty = true;

    public synchronized String tryRecieve() {
        // Wait until message is
        // available.
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Toggle status.
        empty = true;
        // Notify producer that
        // status has changed.
        notifyAll();
        return message;
    }

    public synchronized String take() {
        // get the message
        empty = true;
        // Notify producer that
        // status has changed.
        notifyAll();
        String taken = this.message;
        this.message = null;
        return taken;
    }

    public synchronized void put(String message) {
        // Wait until message has
        // been retrieved.
        while (!empty) {
            try { 
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Toggle status.
        empty = false;
        // Store message.
        this.message = message;
        // Notify consumer that status
        // has changed.
        notifyAll();
    }
    
}
