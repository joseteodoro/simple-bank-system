package plastic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.border.MatteBorder;

public class CardService {

    public static CardService of(List<Brand> list) {
        return new CardService(list);
    }

    private BinarySearch search;

    private Map<String, Brand> cache;

    private CardService(List<Brand> list) {
        this.search = BinarySearch.of(list);
        this.cache = Collections.synchronizedMap(new HashMap<>());
    }

    public String findBrandName(String cardNumber) {
        String prefix = cardNumber.substring(0, 8);
        if (cache.containsKey(prefix)) return cache.get(prefix).getName();

        Optional<Brand> maybe = search.findByPrefix(prefix);
        if (maybe.isPresent()) {
            cache.put(prefix, maybe.get());
        }

        return maybe.isEmpty()
            ? null
            : maybe.get().getName();
    }
    
}
