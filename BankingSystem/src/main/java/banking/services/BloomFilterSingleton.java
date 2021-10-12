package banking.services;

import java.util.concurrent.atomic.AtomicLong;

// just discovered that should be sequencial numbers! XD
public class BloomFilterSingleton {

    private static BloomFilterSingleton instance;

    private AtomicLong accountNumber = new AtomicLong(0);

    // thread safe here!
    static {
        instance = new BloomFilterSingleton();
    }

    public static BloomFilterSingleton getInstance() {
        return instance;
    }

    // have to be thread safe
    public synchronized Long generateAccountNumber() {
        return accountNumber.incrementAndGet();
    }

}
