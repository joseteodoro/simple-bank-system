package plastic;

import java.util.Objects;

public class Brand implements Comparable<Brand> {

    private String name;

    private String startRange;

    private String endRange;

    public Brand(String name, String startRange, String endRange) {
        this.name = name;
        this.startRange = startRange;
        this.endRange = endRange;
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

    @Override
    public int compareTo(Brand obj) {
        return this.startRange.compareTo(obj.getStartRange());
    }
    
}
