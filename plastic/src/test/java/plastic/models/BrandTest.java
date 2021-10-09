package plastic.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import plastic.services.Sanitizer;

public class BrandTest {

    @Test
    public void shouldCompareUnderRangeProperly() {
        assertEquals(-1, Brand.of("upper", "1000", "9999").compareWith(Sanitizer.sanitizePrefix("0999")));
        assertEquals(-1, Brand.of("upper", "00000005", "00000005").compareWith(Sanitizer.sanitizePrefix("00000004")));
    }

    @Test
    public void shouldCompareAboveRangeProperly() {
        assertEquals(1, Brand.of("lower", "1000", "1001").compareWith(Sanitizer.sanitizePrefix("1002")));
        assertEquals(1, Brand.of("lower", "00000005", "00000005").compareWith(Sanitizer.sanitizePrefix("00000006")));
    }

    @Test
    public void shouldCompareInsideRangeProperly() {
        assertEquals(0, Brand.of("lower", "1000", "1001").compareWith(Sanitizer.sanitizePrefix("1000")));
        assertEquals(0, Brand.of("lower", "1000", "2000").compareWith(Sanitizer.sanitizePrefix("1500")));
        assertEquals(0, Brand.of("lower", "1000", "2000").compareWith(Sanitizer.sanitizePrefix("1999")));
        assertEquals(0, Brand.of("lower", "00000005", "00000005").compareWith(Sanitizer.sanitizePrefix("00000005")));
    }
    
}
