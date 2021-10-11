package plastic.services.handmade.cache;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import plastic.models.Brand;
import plastic.services.handmade.cache.CachingService;
import plastic.services.handmade.cache.PurgeType;

public class CachingServiceTest {

    @Test
    public void whenEvictShouldRemoveItemAfterCompact() {
        CachingService service = CachingService.of(10, 1000, PurgeType.LRU);
        Brand brand = Brand.of("sample", "1", "2");
        service.add(brand.getName(), brand);
        assertTrue(service.hasKey(brand.getName()));
        service.evict(brand.getName());
        service.compact();
        assertFalse(service.hasKey(brand.getName()));
    }

    @Test
    public void afterTimeoutShouldCompactAndRemovedExpiredOnes() throws InterruptedException {
        CachingService service = CachingService.of(1, 10, PurgeType.LRU);
        Brand brand1 = Brand.of("sample1", "11", "12");
        service.add(brand1.getName(), brand1);
        assertNotNull(service.get(brand1.getName()));

        Brand brand2 = Brand.of("sample2", "21", "22");
        service.add(brand2.getName(), brand2);
        assertNotNull(service.get(brand2.getName()));

        Thread.sleep(2000);

        service.compact();

        Brand brand3 = Brand.of("sample3", "31", "32");
        service.add(brand3.getName(), brand3);
        assertNotNull(service.get(brand3.getName()));
        assertNull(service.get(brand1.getName()));
        assertNull(service.get(brand2.getName()));
    }
    
    @Test
    public void afterLimitShouldCallCompactOnInsert() throws InterruptedException {
        CachingService service = CachingService.of(1, 1, PurgeType.LRU);
        Brand brand1 = Brand.of("sample1", "11", "12");
        service.add(brand1.getName(), brand1);
        assertNotNull(service.get(brand1.getName()));

        Brand brand2 = Brand.of("sample2", "21", "22");
        service.add(brand2.getName(), brand2);
        assertNotNull(service.get(brand2.getName()));

        Thread.sleep(2000);

        Brand brand3 = Brand.of("sample3", "31", "32");
        service.add(brand3.getName(), brand3);
        assertNotNull(service.get(brand3.getName()));
        assertNull(service.get(brand1.getName()));
        assertNull(service.get(brand2.getName()));
    }
}
