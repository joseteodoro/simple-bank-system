package plastic.services.handmade.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import plastic.models.Brand;
import plastic.services.handmade.cache.Box;

public class BoxTest {

    @Test
    public void useShouldIncrementCounter() throws InterruptedException {
        Box box = Box.of("key", Brand.of("brand name", "0", "1"));
        assertEquals(0, box.getUsageCount());
        Thread.sleep(1000);
        Brand result = box.use();
        assertEquals(1, box.getUsageCount());
        assertEquals("brand name", result.getName());
        assertNotEquals(box.getCreatedAt(), box.getLastUsage());
    }
    
}
