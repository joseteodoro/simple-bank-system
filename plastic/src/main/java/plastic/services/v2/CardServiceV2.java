package plastic.services.v2;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

import plastic.models.Brand;
import plastic.services.CardService;
import plastic.services.Sanitizer;

public class CardServiceV2 implements CardService {

    private static final boolean ENABLE_PURGE_FOR_LRU = true;

    private static final boolean DEFAULT_USE_CACHE = false;

    public static CardServiceV2 of(List<Brand> list) {
        return of(list, DEFAULT_USE_CACHE);
    }

    public static CardServiceV2 of(List<Brand> list, boolean useCache) {
        return new CardServiceV2(list, useCache);
    }

    private TreeMap<Long, Brand> search;

    private LinkedHashMap<String, Brand> cachingService;

    private CardServiceV2(List<Brand> list, boolean useCache) {
        this.search = new TreeMap<>();
        list.stream().forEach(brand -> {
            this.search.put(brand.getStartNumber(), brand);
        });
        if (useCache) {
            cachingService = new LinkedHashMap<>(list.size(), 0.5f, ENABLE_PURGE_FOR_LRU);
        }
    }

    @Override
    public synchronized String findBrandName(String cardNumber) {
        String prefix = cardNumber.substring(0, 8);
        Long prefixNumber = Sanitizer.sanitizePrefix(prefix);

        if (Objects.nonNull(cachingService)) {
            Brand cached = cachingService.get(prefix);
            if (Objects.nonNull(cached)) return cached.getName();
        }

        Optional<Long> maybe = Optional.ofNullable(search.floorKey(prefixNumber));

        if (maybe.isPresent() && Objects.nonNull(cachingService)) {
            cachingService.put(prefix, search.get(maybe.get()));
        }

        return maybe.isEmpty()
            ? null
            : search.get(maybe.get()).getName();
    }

}