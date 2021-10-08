package banking;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

// just discovered that should be sequencial numbers! XD
public class BloomFilterSingleton {

    private static final long leftLimit = 1L;
    
    private static final long rightLimit = 100000000L;

    private static BloomFilterSingleton instance;

    // change it to a serious bloomfilter implementation
    private final Set<Long> items = new HashSet<>();

    private AtomicLong accountNumber = new AtomicLong(0);

    // thread safe here!
    static {
        instance = new BloomFilterSingleton();
    }

    public static BloomFilterSingleton getInstance() {
        return instance;
    }

    // have to be thread safe
    private Long generateRandomLongNumber() {
        //return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        return accountNumber.incrementAndGet();
    }

    public synchronized Long generateAccountNumber() {
        // bloom filter is fast as possible to check existent items in a collection
        // I'm mocking here cause lacking of time!
        Long generatedLong = null;
        do {
            generatedLong = generateRandomLongNumber();
        }
        while (items.contains(generatedLong));
        this.items.add(generatedLong);
        return generatedLong;
    }

}
