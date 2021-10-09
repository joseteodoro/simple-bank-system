package plastic.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import plastic.services.search.CardService;


public class CardServiceTest {
    
    @Test
    public void whenUsingCreditCardsShouldReturnTheBrandProperly() {
        CardService service = CardService.of(new BrandDatabase().listBrandRanges());
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
