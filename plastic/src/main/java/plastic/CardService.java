package plastic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.swing.border.MatteBorder;

public class CardService {

    public static CardService of(List<Brand> list) {
        return new CardService(list);
    }

    private BinarySearch search;

    private CardService(List<Brand> list) {
        this.search = BinarySearch.of(list);
    }

    public String findBrandName(String cardNumber) {
        Optional<Brand> maybe = search.findByPrefix(cardNumber.substring(0, 8));
        return maybe.isEmpty()
            ? null
            : maybe.get().getName();
    }
    
}
