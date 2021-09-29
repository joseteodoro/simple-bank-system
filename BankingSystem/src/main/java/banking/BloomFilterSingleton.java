package banking;

import java.util.HashSet;
import java.util.Set;

public class BloomFilterSingleton {
    

    private static final long leftLimit = 1L;
    
    private static final long rightLimit = 100000000L;

    private static BloomFilterSingleton instance;

    // change it to a serious bloomfilter implementation
    private final Set<Long> items = new HashSet<>();

    // thread safe here!
    static {
        instance = new BloomFilterSingleton();
    }

    public static BloomFilterSingleton getInstance() {
        return instance;
    }

    private Long generateRandomLongNumber() {
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    // have to be thread safe
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
