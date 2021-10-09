package plastic.services.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import plastic.models.Brand;
import plastic.services.Sanitizer;

public class BinarySearch {

    private List<Brand> sortedArrayList;

    private BinarySearch(List<Brand> list) {
        this.sortedArrayList = new ArrayList<>(list);
        Collections.sort(this.sortedArrayList);
    }

    protected int findRange(String prefix) {
        return findRange(Sanitizer.sanitizePrefix(prefix), 0, sortedArrayList.size() - 1);
    }

    private int findRange(Long prefix, int startIndex, int endIndex) {
        if (startIndex == endIndex) return startIndex;
        if (startIndex < 0) return endIndex;
        if (startIndex >  endIndex) return startIndex;

        int middle = startIndex + ((endIndex - startIndex) / 2);
        Brand middleBrand = this.sortedArrayList.get(middle);
        int comparison = middleBrand.compareWith(prefix);

        if (comparison == 0) return middle;

        if (comparison < 0) return findRange(prefix, startIndex, middle - 1);
        if (comparison > 0) return findRange(prefix, middle + 1, endIndex);
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
