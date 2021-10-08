package plastic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BinarySearchTest {
    
    @Test
    public void whenSingleElementArrayDoesntCrash() { 
        BinarySearch search = BinarySearch.of(
            List.of(
                Brand.of("Visa", "49290000", "49299999")
            )
        );
        int index = search.findRange("492");
        assertEquals(0, index);
        Brand value = search.findByPrefix("492").orElse(null);
        assertNotNull(value);
        assertEquals("Visa", value.getName());
    }

    @Test
    public void whenDesiderIndexIsFirstShouldWorkProperly() {
        BinarySearch search = BinarySearch.of(
            List.of(
                Brand.of("JCB", "35280000", "35289999"),
                Brand.of("AMEX", "37000000", "37999999"),
                Brand.of("Visa", "49290000", "49299999")
            )
        );
        int index = search.findRange("35280001");
        assertEquals(0, index);

        Brand value = search.findByPrefix("35280001").orElse(null);
        assertNotNull(value);
        assertEquals("JCB", value.getName());
    }

    @Test
    public void whenDesiderIndexIsInTheMiddleShouldWorkProperly() {
        BinarySearch search = BinarySearch.of(
            List.of(
                Brand.of("JCB", "35280000", "35289999"),
                Brand.of("AMEX", "37000000", "37999999"),
                Brand.of("Visa", "49290000", "49299999")
            )
        );
        int index = search.findRange("37000001");
        assertEquals(1, index);
        Brand value = search.findByPrefix("37000001").orElse(null);
        assertNotNull(value);
        assertEquals("AMEX", value.getName());
    }

    @Test
    public void whenHaveLotOfItemsShouldWorkProperly() {
        BinarySearch search = BinarySearch.of(
            List.of(
                Brand.of("Diners", "30000000", "30099999"),
                Brand.of("Diners", "30100000", "30199999"),
                Brand.of("JCB", "35280000", "35289999"),
                Brand.of("AMEX", "37000000", "37999999"),
                Brand.of("Visa", "49290000", "49299999")
            )
        );
        int index = search.findRange("37000001");
        assertEquals(3, index);
        Brand value = search.findByPrefix("37000001").orElse(null);
        assertNotNull(value);
        assertEquals("AMEX", value.getName());
    }

    @Test
    public void whenDesiderIndexIsInEndShouldWorkProperly() {
        BinarySearch search = BinarySearch.of(
            List.of(
                Brand.of("JCB", "35280000", "35289999"),
                Brand.of("AMEX", "37000000", "37999999"),
                Brand.of("Visa", "49290000", "49299999")
            )
        );
        int index = search.findRange("49290001");
        assertEquals(2, index);
        Brand value = search.findByPrefix("49290001").orElse(null);
        assertNotNull(value);
        assertEquals("Visa", value.getName());
    }

    @Test
    public void whenHaveLotOfItemsWithBrandInTheEndShouldWorkProperly() {
        BinarySearch search = BinarySearch.of(
            List.of(
                Brand.of("Diners", "30000000", "30099999"),
                Brand.of("Diners", "30100000", "30199999"),
                Brand.of("JCB", "35280000", "35289999"),
                Brand.of("AMEX", "37000000", "37999999"),
                Brand.of("Visa", "49290000", "49299999")
            )
        );
        int index = search.findRange("49299998");
        assertEquals(4, index);
        Brand value = search.findByPrefix("49299998").orElse(null);
        assertNotNull(value);
        assertEquals("Visa", value.getName());
    }

}
