package plastic.services.v2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import plastic.services.BrandDatabase;
import plastic.services.SampleDatabase;


public class CardServiceV2Test {
    
    @Test
    public void whenUsingCreditCardsShouldReturnTheBrandProperly() {
        CardServiceV2 service = CardServiceV2.of(new BrandDatabase().listBrandRanges());
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
