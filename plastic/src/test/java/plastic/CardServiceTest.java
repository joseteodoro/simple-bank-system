package plastic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CardServiceTest {
    
    @Test
    public void whenUsingCreditCardsShouldReturnTheBrandProperly() {
        CardService service = CardService.of(new BrandDatabase().listBrandRanges());
        SampleImmutableDatabase.entries.stream()
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
