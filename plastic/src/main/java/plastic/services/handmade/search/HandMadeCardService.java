package plastic.services.handmade.search;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import plastic.models.Brand;
import plastic.services.CardService;
import plastic.services.handmade.cache.CachingService;
import plastic.services.handmade.cache.PurgeType;

public class HandMadeCardService implements CardService {

    private static final boolean DEFAULT_USE_CACHE = false;

    private static final int DEFAULT_CACHE_TTL_SECONDS = 10;

    private static final int DEFAULT_CACHE_MAX_SIZE = 1000;

    private static final PurgeType DEFAULT_CACHE_PURGE_STRATEGY = PurgeType.LRU;

    public static HandMadeCardService of(List<Brand> list) {
        return of(list, DEFAULT_USE_CACHE);
    }

    public static HandMadeCardService of(List<Brand> list, boolean useCache) {
        return new HandMadeCardService(list, useCache);
    }

    private BinarySearch search;

    private CachingService cachingService = null;

    private HandMadeCardService(List<Brand> list, boolean useCache) {
        this.search = BinarySearch.of(list);
        if (useCache) {
            cachingService = CachingService.of(DEFAULT_CACHE_TTL_SECONDS, DEFAULT_CACHE_MAX_SIZE, DEFAULT_CACHE_PURGE_STRATEGY);
        }
    }

    @Override
    public String findBrandName(String cardNumber) {
        String prefix = cardNumber.substring(0, 8);
        if (Objects.nonNull(cachingService)) {
            Brand cached = cachingService.get(prefix);
            if (Objects.nonNull(cached)) return cached.getName();
        }

        Optional<Brand> maybe = search.findByPrefix(prefix);
        if (maybe.isPresent() && Objects.nonNull(cachingService)) {
            cachingService.add(prefix, maybe.get());
        }

        return maybe.isEmpty()
            ? null
            : maybe.get().getName();
    }
    
}
