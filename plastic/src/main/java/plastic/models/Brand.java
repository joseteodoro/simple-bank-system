package plastic.models;

import plastic.services.Sanitizer;

public class Brand implements Comparable<Brand> {

    private final String name;

    private final String startRange;

    private final String endRange;

    private final Long startNumber;

    private final long endNumber;

    public Brand(String name, String startRange, String endRange) {
        this.name = name;
        this.startRange = startRange;
        this.endRange = endRange;
        this.startNumber = Sanitizer.sanitizePrefix(startRange);
        this.endNumber = Sanitizer.sanitizePrefix(endRange);
    }

    public String getName() {
        return name;
    }

    public String getStartRange() {
        return startRange;
    }

    public String getEndRange() {
        return endRange;
    }

    public static Brand of(String name, String startRange, String endRange) {
        return new Brand(name, startRange, endRange);
    }

    public long getEndNumber() {
        return endNumber;
    }

    public Long getStartNumber() {
        return startNumber;
    }

    @Override
    public int compareTo(Brand obj) {
        return this.startNumber.compareTo(obj.startNumber);
    }

    public int compareWith(Long cardPrefix) {
        if (cardPrefix < this.startNumber) return -1;
        if (this.endNumber < cardPrefix) return 1;
        return 0;
    }
    
}
