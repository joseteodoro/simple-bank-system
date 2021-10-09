package plastic.services.cache;

import java.util.Comparator;
import java.util.Objects;

public class PurgerStrategy {

    private PurgerStrategy () {}

    private static final int FACTOR = 10;

    private static int compareLastUsage(Box l, Box r) {
        return (int) (r.getLastUsage() - l.getLastUsage());
    }

    private static int compareCommonUsage(Box l, Box r) {
        return (int) (l.getUsageCount() - r.getUsageCount());
    }

    public static Comparator<Box> of(PurgeType type) {
        Comparator<Box> lru = (l, r) -> {
            if (Objects.isNull(l.getValue())) return -1;
            if (Objects.isNull(r.getValue())) return 1;
            return compareLastUsage(l, r);
        };
        Comparator<Box> lcu = (l, r) -> {
            if (Objects.isNull(l.getValue())) return -1;
            if (Objects.isNull(r.getValue())) return 1;
            return compareCommonUsage(l, r);
        };
        Comparator<Box> lruLcu = (l, r) -> {
            if (Objects.isNull(l.getValue())) return -1;
            if (Objects.isNull(r.getValue())) return 1;
            return (compareCommonUsage(l, r) * FACTOR) + compareLastUsage(l, r);
        };

        if (PurgeType.LCU.equals(type)) return lcu;
        if (PurgeType.LRU_LCU.equals(type)) return lruLcu;
        return lru;
    }
    
}
