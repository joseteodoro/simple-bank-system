package plastic.services.handmade.search;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import plastic.services.BrandDatabase;
import plastic.services.SampleDatabase;
import plastic.services.handmade.search.HandMadeCardService;


public class CardServiceTest {
    
    @Test
    public void whenUsingCreditCardsShouldReturnTheBrandProperly() {
        HandMadeCardService service = HandMadeCardService.of(new BrandDatabase().listBrandRanges());
        SampleDatabase.smallCardSample().stream()
            .forEach(entry -> {
                String brandName = service.findBrandName(entry.getCard().getCardNumber());
                assertEquals(
                    String.join(" ", "failed", brandName,
                        "should be", entry.getBrand().getName(),
                        "for card", entry.getCard().getCardNumber()),
                    entry.getBrand().getName(),
                    brandName);
            });
    }

}
