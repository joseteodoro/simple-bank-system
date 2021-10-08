package plastic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BinarySearch {

    private List<Brand> sortedArrayList;

    private BinarySearch(List<Brand> list) {
        this.sortedArrayList = new ArrayList<>(list);
        Collections.sort(this.sortedArrayList);
    }

    protected int findRange(String prefix) {
        return findRange(prefix, 0, sortedArrayList.size() - 1);
    }

    private int findRange(String prefix, int startIndex, int endIndex) {
        if (startIndex == endIndex) return startIndex;
        if (startIndex < 0) return endIndex;
        if (startIndex >  endIndex) return startIndex;

        int middle = startIndex + ((endIndex - startIndex) / 2);
        Brand middleBrand = this.sortedArrayList.get(middle);
        int left = prefix.compareTo(middleBrand.getStartRange());
        int right = prefix.compareTo(middleBrand.getEndRange());

        if (left > 0 && right < 0) return middle;

        if (left < 0) return findRange(prefix, startIndex, middle - 1);
        if (right > 0) return findRange(prefix, middle + 1, endIndex);
        return middle;
    }

    public Optional<Brand> findByPrefix(String prefix) {
        int position = findRange(prefix);
        return position >= 0 || position > sortedArrayList.size() - 1
            ? Optional.of(this.sortedArrayList.get(position))
            : Optional.empty();
    }

    public static BinarySearch of(List<Brand> list) {
        return new BinarySearch(list);
    }

}
