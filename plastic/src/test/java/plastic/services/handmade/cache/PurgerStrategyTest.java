package plastic.services.handmade.cache;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import plastic.models.Brand;
import plastic.services.handmade.cache.Box;
import plastic.services.handmade.cache.PurgeType;
import plastic.services.handmade.cache.PurgerStrategy;

public class PurgerStrategyTest {

    @Test
    public void LNullShouldReturnR() {
        Box l = Box.of("key0", null);
        Box r = Box.of("key1", Brand.of("key1", "1", "2"));
        List<Box> list = Arrays.asList(l, r);
        Collections.sort(list, PurgerStrategy.of(PurgeType.LCU));
        assertEquals("key0", list.get(0).getKey());
    }

    @Test
    public void RNullShouldReturnL() {
        Box l = Box.of("key1", null);
        Box r = Box.of("key0", Brand.of("key0", "1", "2"));
        List<Box> list = Arrays.asList(l, r);
        Collections.sort(list, PurgerStrategy.of(PurgeType.LCU));
        assertEquals("key1", list.get(0).getKey());
    }

    @Test
    public void LRNullShouldReturnL() {
        Box l = Box.of("key1", null);
        Box r = Box.of("key0", null);
        List<Box> list = Arrays.asList(l, r);
        Collections.sort(list, PurgerStrategy.of(PurgeType.LCU));
        assertEquals("key0", list.get(0).getKey());
    }

    @Test
    public void whenUsingLCUAndWhenLmoreUsedShouldReturnR() {
        Box l = Box.of("key0", Brand.of("key0", "1", "2"));
        l.use();
        l.use();
        l.use();
        Box r = Box.of("key1", Brand.of("key1", "1", "2"));
        r.use();
        List<Box> list = Arrays.asList(l, r);
        Collections.sort(list, PurgerStrategy.of(PurgeType.LCU));
        assertEquals("key1", list.get(0).getKey());
    }
    
    @Test
    public void whenUsingLRUAndWhenLmoreUsedShouldReturnR() throws InterruptedException {
        Box l = Box.of("key0", Brand.of("value0", "1", "2"));
        l.use();
        Box r = Box.of("key1", Brand.of("value1", "1", "2"));
        r.use();
        Thread.sleep(1000);
        l.use();
        List<Box> list = Arrays.asList(l, r);
        Collections.sort(list, PurgerStrategy.of(PurgeType.LCU));
        assertEquals("key1", list.get(0).getKey());
    }

    @Test
    public void whenUsingLRU_LCUAndWhenLmoreUsedShouldReturnL() throws InterruptedException {
        Box l = Box.of("key0", Brand.of("value0", "1", "2"));
        l.use();
        Box r = Box.of("key1", Brand.of("value1", "1", "2"));
        r.use();
        r.use();
        r.use();
        Thread.sleep(1000);
        l.use();
        List<Box> list = Arrays.asList(l, r);
        Collections.sort(list, PurgerStrategy.of(PurgeType.LCU));
        assertEquals("key0", list.get(0).getKey());
    }

}
