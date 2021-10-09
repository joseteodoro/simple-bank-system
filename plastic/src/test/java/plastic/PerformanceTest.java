package plastic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import org.junit.Test;

import plastic.models.Brand;
import plastic.models.CardEntry;
import plastic.services.BrandDatabase;
import plastic.services.CardService;
import plastic.services.SampleDatabase;

public class PerformanceTest {

    private final static int FIVE_SECONDS = 5000;

    private final static long LARGE_NUMBER = 1000000l;

    private boolean compare(CardEntry expected, String foundBrand) {
        return expected.getBrand().getName().equals(foundBrand);
    }

    @Test(timeout = FIVE_SECONDS)
    public void shouldPerformGoodWithLargeCardSampleAndFewBrandRanges() throws IOException {
        LocalTime start = LocalTime.now();
        System.out.println("started at " + start);
        List<Brand> brands = new BrandDatabase().listBrandRanges();
        CardService service = CardService.of(brands);

        long count = SampleDatabase.largeCardSample(LARGE_NUMBER, brands).parallelStream()
            .map(entry -> {
                String foundBrand = service.findBrandName(entry.getCard().getCardNumber());
                return compare(entry, foundBrand);
            })
            .filter(res -> res)
            .count();

        assertTrue(count > 0);
        System.out.println("created: " + count + " branded cards");
        LocalTime finished = LocalTime.now();
        System.out.println("Finished at " + finished);
    }

    @Test(timeout = FIVE_SECONDS)
    public void shouldPerformGoodWithLargeCardSampleAndLargeBrandRanges() throws IOException {
        LocalTime start = LocalTime.now();
        System.out.println("started at " + start);
        List<Brand> brands = SampleDatabase.largeBrandSample(LARGE_NUMBER);
        CardService service = CardService.of(brands);

        long count = SampleDatabase.largeCardSample(LARGE_NUMBER, brands).parallelStream()
            .map(entry -> {
                String foundBrand = service.findBrandName(entry.getCard().getCardNumber());
                return compare(entry, foundBrand);
            })
            .filter(res -> res)
            .count();

        assertTrue(count > 0);
        System.out.println("created: " + count + " branded cards");
        LocalTime finished = LocalTime.now();
        System.out.println("Finished at " + finished);
    }

    @Test(timeout = FIVE_SECONDS)
    public void shouldPerformGoodWithMediumCardSampleAndMediumBrandRanges() throws IOException {
        LocalTime start = LocalTime.now();
        System.out.println("started at " + start);
        List<Brand> brands = SampleDatabase.midBrandSample();
        CardService service = CardService.of(brands);
        List<CardEntry> cards = SampleDatabase.midCardSample();

        long count = cards.parallelStream()
            .map(entry -> {
                String foundBrand = service.findBrandName(entry.getCard().getCardNumber());
                return compare(entry, foundBrand);
            })
            .filter(res -> res)
            .count();

        assertEquals(cards.size(), count);
        System.out.println("created: " + count + " branded cards");
        LocalTime finished = LocalTime.now();
        System.out.println("Finished at " + finished);
    }

}
